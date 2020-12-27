package com.hbhb.cw.invoice.web.controller;

import com.alibaba.excel.EasyExcel;
import com.hbhb.core.utils.ExcelUtil;
import com.hbhb.cw.invoice.common.config.InvoiceErrorCode;
import com.hbhb.cw.invoice.common.exception.InvoiceException;
import com.hbhb.cw.invoice.model.Page;
import com.hbhb.cw.invoice.rpc.FileApiExp;
import com.hbhb.cw.invoice.service.InvoiceAccountRunService;
import com.hbhb.cw.invoice.service.listener.InvoiceAccountRunListener;
import com.hbhb.cw.invoice.web.vo.InvoiceAccountRunExportVO;
import com.hbhb.cw.invoice.web.vo.InvoiceAccountRunImportVO;
import com.hbhb.cw.invoice.web.vo.InvoiceAccountRunResVO;
import com.hbhb.cw.invoice.web.vo.InvoiceAccountRunVO;
import com.hbhb.web.annotation.UserId;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
/**
 * @author yzc
 * @since 2020-10-19
 */
@Tag(name = "渠道发票-往来账管理")
@RestController
@RequestMapping("/account/run")
@Slf4j
public class InvoiceAccountRunController {

    @Resource
    private FileApiExp fileApi;

    @Resource
    private InvoiceAccountRunService invoiceAccountRunService;


    @Operation(summary = "按条件获取列表")
    @GetMapping("/list")
    public Page<InvoiceAccountRunResVO> getPages(
            @Parameter(description ="条件") InvoiceAccountRunVO cond,
            @Parameter(description = "页码，默认为1") @RequestParam(required = false) Integer pageNum,
            @Parameter(description = "每页数量，默认为10") @RequestParam(required = false) Integer pageSize,
            @Parameter(hidden = true) @UserId Integer userId) {
        pageNum = pageNum == null ? 1 : pageNum;
        pageSize = pageSize == null ? 20 : pageSize;
        return invoiceAccountRunService.getPageByCont(cond, userId, pageNum, pageSize);
    }

    @Operation(summary ="往来账管理导入")
    @PostMapping("/import")
    public List<String> importList(MultipartFile file,
                                    @Parameter(hidden = true) @UserId Integer userId) {
        long begin = System.currentTimeMillis();
        try {
            EasyExcel.read(file.getInputStream(), InvoiceAccountRunImportVO.class,
                    new InvoiceAccountRunListener(invoiceAccountRunService, new AtomicInteger(userId)))
                    .headRowNumber(4).sheet().doRead();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new InvoiceException(InvoiceErrorCode.INVOICE_DATA_IMPORT_ERROR);
        }
        log.info("往来账管理表导入结束，总共耗时：" + (System.currentTimeMillis() - begin) / 1000 + "s");
        return invoiceAccountRunService.getMsg();
    }

    @Operation(summary ="导出往来账管理表")
    @PostMapping("/export")
    public void accountTemplate(HttpServletRequest request, HttpServletResponse response,
                                @RequestBody InvoiceAccountRunVO cond,
                                 @Parameter(hidden = true) @UserId Integer userId) {
        List<InvoiceAccountRunExportVO> list = invoiceAccountRunService.getListByCont(cond,userId);
        String fileName = ExcelUtil.encodingFileName(request, "往来账管理表");
        ExcelUtil.export2WebWithTemplate(response, fileName, "往来账",
                "C:/Users/cn/Desktop/lll" + File.separator + "往来账管理表.xlsx", list);
    }

    @Operation(summary ="导出往来账管理模板")
    @PostMapping("/export/template")
    public void rewardTemplate(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<Object> list = new ArrayList<>();
        String fileName = ExcelUtil.encodingFileName(request, "往来账数据导入模板");
        ExcelUtil.export2WebWithTemplate(response, fileName, "Sheet1",
                fileApi.getTemplatePath() + File.separator + "往来账数据导入模板.xlsx", list);
    }

    @Operation(summary ="往来帐导入时间")
    @GetMapping("/time")
    public String getTime(Integer unitId,  @Parameter(hidden = true) @UserId Integer userId) {
        if (unitId == null) {
            return invoiceAccountRunService.getTime(userId);
        } else {
            return invoiceAccountRunService.getTime(unitId);
        }
    }
}
