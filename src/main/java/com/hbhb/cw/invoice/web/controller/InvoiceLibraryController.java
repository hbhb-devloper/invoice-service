package com.hbhb.cw.invoice.web.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.hbhb.cw.invoice.common.config.InvoiceErrorCode;
import com.hbhb.cw.invoice.common.exception.InvoiceException;
import com.hbhb.cw.invoice.service.InvoiceLibraryService;
import com.hbhb.cw.invoice.service.listener.InvoiceGoodsListener;
import com.hbhb.cw.invoice.service.listener.InvoiceLibraryListener;
import com.hbhb.cw.invoice.web.vo.InvoiceGoodsImportVO;
import com.hbhb.cw.invoice.web.vo.InvoiceLibraryImportVO;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yzc
 * @since 2020-09-15
 */
@Tag(name = "渠道发票")
@RestController
@RequestMapping("/library")
@Slf4j
public class InvoiceLibraryController {

    @Resource
    private InvoiceLibraryService invoiceLibraryService;


    @Operation(summary ="发票库表导入")
    @PostMapping("/import")
    public void importLibrary(MultipartFile file) {
        long begin = System.currentTimeMillis();
        try {
            ExcelReader build = EasyExcel.read(file.getInputStream()).build();
            ReadSheet sheet1 = EasyExcel.readSheet(0).head(InvoiceLibraryImportVO.class)
                    .registerReadListener(new InvoiceLibraryListener(invoiceLibraryService)).headRowNumber(2).build();
            ReadSheet sheet2 = EasyExcel.readSheet(1).head(InvoiceGoodsImportVO.class)
                    .registerReadListener(new InvoiceGoodsListener(invoiceLibraryService)).headRowNumber(2).build();
            build.read(sheet1,sheet2);
            build.finish();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new InvoiceException(InvoiceErrorCode.INVOICE_DATA_IMPORT_ERROR);
        }
        log.info("税务据库表模板导入结束，总共耗时：" + (System.currentTimeMillis() - begin) / 1000 + "s");
    }


    @Operation(summary ="发票库表导入时间")
    @GetMapping("/time")
    public String getTime() {
        return invoiceLibraryService.getTime();
    }

}
