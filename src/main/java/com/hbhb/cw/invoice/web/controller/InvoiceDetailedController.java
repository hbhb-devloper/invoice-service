package com.hbhb.cw.invoice.web.controller;

import com.alibaba.excel.EasyExcel;
import com.hbhb.core.utils.ExcelUtil;
import com.hbhb.cw.invoice.common.config.InvoiceErrorCode;
import com.hbhb.cw.invoice.common.exception.InvoiceException;
import com.hbhb.cw.invoice.model.Page;
import com.hbhb.cw.invoice.rpc.FileApiExp;
import com.hbhb.cw.invoice.service.InvoiceDetailedService;
import com.hbhb.cw.invoice.service.listener.InvoiceDetailedListener;
import com.hbhb.cw.invoice.web.vo.InvoiceDetailedExportVO;
import com.hbhb.cw.invoice.web.vo.InvoiceDetailedImportVO;
import com.hbhb.cw.invoice.web.vo.InvoiceDetailedVO;
import com.hbhb.cw.invoice.web.vo.InvoiceRewardDetailedResVO;
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
 * @since 2020-10-21
 */
@Tag(name = "渠道发票-酬金计提明细管理表")
@RestController
@RequestMapping("/detailed")
@Slf4j
public class InvoiceDetailedController {

    @Resource
    private InvoiceDetailedService invoiceDetailedService;

    @Resource
    private FileApiExp fileApi;

    @Operation(summary = "按条件获取列表")
    @GetMapping("/list")
    public Page<InvoiceRewardDetailedResVO> getPages(
            @Parameter(description ="条件") InvoiceDetailedVO cond,
           @Parameter(description = "页码，默认为1") @RequestParam(required = false) Integer pageNum,
           @Parameter(description = "每页数量，默认为10") @RequestParam(required = false) Integer pageSize,
            @Parameter(hidden = true) @UserId Integer userId) {
        pageNum = pageNum == null ? 1 : pageNum;
        pageSize = pageSize == null ? 20 : pageSize;
        return invoiceDetailedService.getPageByCont(cond, userId, pageNum, pageSize);
    }


    @Operation(summary ="酬金计提明细管理表导入")
    @PostMapping("/import")
    public List<String> importList(MultipartFile file,
                                   @Parameter(hidden = true) @UserId Integer userId) {
        long begin = System.currentTimeMillis();
        try {
            EasyExcel.read(file.getInputStream(), InvoiceDetailedImportVO.class,
                    new InvoiceDetailedListener(invoiceDetailedService, new AtomicInteger(userId))).sheet()
                    .headRowNumber(2).doRead();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new InvoiceException(InvoiceErrorCode.INVOICE_DATA_IMPORT_ERROR);
        }
        log.info("酬金发放表导入结束，总共耗时：" + (System.currentTimeMillis() - begin) / 1000 + "s");
        return invoiceDetailedService.getMsg();
    }

    @Operation(summary ="导出酬金计提明细管理表")
    @PostMapping("/export")
    public void accountTemplate(HttpServletRequest request, HttpServletResponse response,
                                @RequestBody InvoiceDetailedVO cond,
                                @Parameter(hidden = true) @UserId Integer userId) {
        List<InvoiceDetailedExportVO> list = invoiceDetailedService.getListByCont(cond,userId);
        String fileName = ExcelUtil.encodingFileName(request, "酬金计提明细管理表");
        ExcelUtil.export2WebWithTemplate(response, fileName, "酬金计提明细",
                fileApi.getTemplatePath() + File.separator + "酬金计提明细管理表.xlsx", list);
    }


    @Operation(summary ="导出酬金计提明细管理模板")
    @PostMapping("/export/template")
    public void rewardTemplate(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<Object> list = new ArrayList<>();
        String fileName = ExcelUtil.encodingFileName(request, "酬金计提明细管理表");
        ExcelUtil.export2WebWithTemplate(response, fileName, "酬金计提明细",
                fileApi.getTemplatePath() + File.separator + "酬金计提明细管理表.xlsx", list);
    }
}
