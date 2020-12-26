package com.hbhb.cw.invoice.web.controller;

import com.alibaba.excel.EasyExcel;
import com.hbhb.core.utils.ExcelUtil;
import com.hbhb.cw.invoice.common.config.InvoiceErrorCode;
import com.hbhb.cw.invoice.common.exception.InvoiceException;
import com.hbhb.cw.invoice.service.InvoiceTaxpayerService;
import com.hbhb.cw.invoice.service.listener.InvoiceTaxpayerListener;
import com.hbhb.cw.invoice.web.vo.InvoiceTaxpayerImportVO;
import com.hbhb.cw.invoice.web.vo.InvoiceTaxpayerShowVO;
import com.hbhb.cw.invoice.web.vo.InvoiceTaxpayerVO;
import com.hbhb.springboot.web.view.Page;
import com.hbhb.web.annotation.UserId;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yzc
 * @since 2020-10-19
 */
@Api(tags = "渠道发票-渠道纳税人资质")
@RestController
@RequestMapping("/invoice/taxpayer")
@Slf4j
public class InvoiceTaxpayerController {

    @Value("${file.upload.template}")
    private String filePath;

    @Resource
    private InvoiceTaxpayerService invoiceTaxpayerService;

    @ApiOperation(value = "按条件获取纳税人资质列表", notes = "分页")
    @GetMapping("/list")
    public Page<InvoiceTaxpayerShowVO> getPages(
            @ApiParam(value = "条件") InvoiceTaxpayerVO cond,
            @ApiParam(value = "页码，默认为1") @RequestParam(required = false) Integer pageNum,
            @ApiParam(value = "每页数量，默认为10") @RequestParam(required = false) Integer pageSize) {
        pageNum = pageNum == null ? 1 : pageNum;
        pageSize = pageSize == null ? 20 : pageSize;
        return invoiceTaxpayerService.getPageByCont(cond, pageNum, pageSize);
    }


    @ApiOperation("纳税资质人导入")
    @PostMapping("/import")
    public List<String> importList(MultipartFile file,  @Parameter(hidden = true) @UserId Integer userId) {
        long begin = System.currentTimeMillis();
        try {
            EasyExcel.read(file.getInputStream(), InvoiceTaxpayerImportVO.class,
                    new InvoiceTaxpayerListener(invoiceTaxpayerService, new AtomicInteger(userId)))
                    .sheet().headRowNumber(2).doRead();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new InvoiceException(InvoiceErrorCode.INVOICE_DATA_IMPORT_ERROR);
        }
        log.info("纳税人资质导入结束，总共耗时：" + (System.currentTimeMillis() - begin) / 1000 + "s");
        return invoiceTaxpayerService.getMsg();
    }

    @ApiOperation("导出渠道纳税人资质模板")
    @PostMapping("/export/template")
    public void rewardTemplate(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<Object> list = new ArrayList<>();
        String fileName = ExcelUtil.encodingFileName(request, "纳税人资质库模块");
        ExcelUtil.export2WebWithTemplate(response, fileName, "Sheet1",
                filePath + File.separator + "纳税人资质库模块.xlsx", list);
    }
}
