package com.hbhb.cw.invoice.web.controller;

import com.hbhb.core.utils.DateUtil;
import com.hbhb.core.utils.ExcelUtil;
import com.hbhb.cw.invoice.service.InvoiceInspectionService;
import com.hbhb.cw.invoice.web.vo.InvoiceInspectionExportVO;
import com.hbhb.cw.invoice.web.vo.InvoiceInspectionReqVO;
import com.hbhb.cw.invoice.web.vo.InvoiceInspectionResVO;
import com.hbhb.web.annotation.UserId;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author yzc
 * @since 2020-09-21
 */
@Api(tags = "渠道发票-校验展示")
@RestController
@RequestMapping("/invoice/inspection")
@Slf4j
public class InvoiceInspectionController {

    @Value("${file.upload.template}")
    private String filePath;

    @Resource
    private InvoiceInspectionService invoiceInspectionService;

    @ApiOperation("发票库表列表")
    @GetMapping("/list")
    public InvoiceInspectionResVO getList(
            @ApiParam(value = "条件") InvoiceInspectionReqVO cond,
            @ApiParam(value = "页码，默认为1") @RequestParam(required = false) Integer pageNum,
            @ApiParam(value = "每页数量，默认为10") @RequestParam(required = false) Integer pageSize,
            @ApiIgnore  @Parameter(hidden = true) @UserId Integer userId) {
        if (cond.getImportDate() == null) {
            cond.setImportDate(DateUtil.getCurrentMonth());
        }
        pageNum = pageNum == null ? 0 : pageNum-1;
        pageSize = pageSize == null ? 20 : pageSize;
        if (cond.getUnitId() == null) {
            cond.setUnitId(userId);
        }
        return invoiceInspectionService.getList(cond, pageNum, pageSize);
    }

    @ApiOperation("导出往来账模板")
    @PostMapping("/export")
    public void accountTemplate(HttpServletRequest request, HttpServletResponse response,
                                @ApiParam(value = "条件") @RequestBody InvoiceInspectionReqVO cond,
                                @Parameter(hidden = true) @UserId Integer userId) {
        if (cond.getImportDate() == null) {
            cond.setImportDate(DateUtil.getCurrentMonth());
        }
        if (cond.getUnitId() == null) {
            cond.setUnitId(userId);
        }
        List<InvoiceInspectionExportVO> list = invoiceInspectionService.getExportList(cond);
        String fileName = ExcelUtil.encodingFileName(request, "增值税发票查验模板");
        ExcelUtil.export2WebWithTemplate(response, fileName, "增值税发票查验",
                filePath + File.separator + "增值税发票查验模板.xlsx", list);
    }
}
