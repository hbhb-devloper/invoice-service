package com.hbhb.cw.invoice.web.controller;

import com.hbhb.core.utils.ExcelUtil;
import com.hbhb.cw.invoice.rpc.FileApiExp;
import com.hbhb.cw.invoice.service.InvoiceContrastService;
import com.hbhb.cw.invoice.web.vo.InvoiceContrastExportVO;
import com.hbhb.cw.invoice.web.vo.InvoiceContrastReqVO;
import com.hbhb.cw.invoice.web.vo.InvoiceContrastResVO;
import com.hbhb.web.annotation.UserId;

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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yzc
 * @since 2020-10-22
 */
@Tag(name = "渠道发票-对比表")
@RestController
@RequestMapping("/contrast")
@Slf4j
public class InvoiceContrastController {

    @Resource
    private FileApiExp fileApi;
    @Resource
    private InvoiceContrastService invoiceContrastService;

    @Operation(summary ="按条件获取列表")
    @GetMapping("/list")
    public InvoiceContrastResVO getPages(
            @Parameter(description ="条件") InvoiceContrastReqVO cond,
           @Parameter(description = "页码，默认为1") @RequestParam(required = false) Integer pageNum,
           @Parameter(description = "每页数量，默认为10") @RequestParam(required = false) Integer pageSize,
            @Parameter(hidden = true) @UserId Integer userId) {
        pageNum = pageNum == null ? 0 : pageNum - 1;
        pageSize = pageSize == null ? 20 : pageSize + 1;
        if (cond.getUnitId() == null) {
            cond.setUnitId(userId);
        }
        return invoiceContrastService.getPageByCont(cond, pageNum, pageSize);
    }

    @Operation(summary ="导出对比表模板")
    @PostMapping("/export/contrast")
    public void rewardTemplate(HttpServletRequest request, HttpServletResponse response,
                               @RequestBody InvoiceContrastReqVO cond,
                               @Parameter(hidden = true) @UserId Integer userId) {
        if (cond.getUnitId() == null) {
            cond.setUnitId(userId);
        }
        List<InvoiceContrastExportVO> list = invoiceContrastService.getExportByCont(cond);
        String fileName = ExcelUtil.encodingFileName(request, "对比表");
        ExcelUtil.export2WebWithTemplate(response, fileName, "对比表",
                fileApi.getTemplatePath() + File.separator + "对比表.xlsx", list);
    }
}
