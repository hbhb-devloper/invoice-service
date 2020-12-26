package com.hbhb.cw.invoice.web.controller;

import com.hbhb.core.utils.ExcelUtil;
import com.hbhb.cw.invoice.common.config.InvoiceErrorCode;
import com.hbhb.cw.invoice.common.exception.InvoiceException;
import com.hbhb.cw.invoice.model.Invoice6vat;
import com.hbhb.cw.invoice.model.Page;
import com.hbhb.cw.invoice.rpc.FileApiExp;
import com.hbhb.cw.invoice.service.Invoice6vatService;
import com.hbhb.cw.invoice.web.vo.Invoice6AddVO;
import com.hbhb.cw.invoice.web.vo.Invoice6ResVO;
import com.hbhb.cw.invoice.web.vo.Invoice6VO;
import com.hbhb.cw.invoice.web.vo.Invoice6vatReqVO;
import com.hbhb.web.annotation.UserId;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

/**
 * 6%增值税专票Controller
 *
 * @author ruoyi
 * @date 2020-06-02
 */
@Tag(name = "发票相关-6%增值税专票")
@RestController
@RequestMapping("/invoice_6vat")
public class Invoice6vatController {

    @Resource
    private Invoice6vatService invoice6vatService;

    @Resource
    private FileApiExp fileApi;

    @Operation(summary ="查询6%增值税专票列表")
    @GetMapping("/list")
    public Page<Invoice6ResVO> list(
           @Parameter(description = "页码，默认为1") @RequestParam(required = false) Integer pageNum,
           @Parameter(description = "每页数量，默认为10") @RequestParam(required = false) Integer pageSize,
            Invoice6vatReqVO invoice6vatReqVO, @Parameter(hidden = true) @UserId Integer userId) {
        pageNum = pageNum == null ? 1 : pageNum;
        pageSize = pageSize == null ? 20 : pageSize;
        //通过用户登入信息得到用户名，赋值于导入人员
        invoice6vatReqVO.setUserId(userId);
        return invoice6vatService
                .selectInvoice6vatByCond(pageNum, pageSize, invoice6vatReqVO);
    }

    @Operation(summary ="新增保存6%增值税专票")
    @PostMapping("")
    public void addSave(@RequestBody Invoice6AddVO invoice6AddVO,
            @Parameter(hidden = true) @UserId Integer userId) {
        Invoice6vat invoice6vat1 = invoice6vatService
                .selectInvoice6vatByNumber(invoice6AddVO);
        if (invoice6vat1 != null) {
            throw new InvoiceException(InvoiceErrorCode.INVOICE_ALREADY_EXISTS);
        }
        invoice6vatService.insertInvoice6vat(invoice6AddVO, userId);
    }


    @Operation(summary ="修改保存6%增值税专票")
    @PutMapping("")
    public void editSave(@RequestBody Invoice6vat invoice6vat) {
        invoice6vatService.updateInvoice6vat(invoice6vat);
    }


    @Operation(summary ="批量删除6%增值税专票")
    @DeleteMapping("/batch")
    public void removes(
            @Parameter(description ="税票id（逗号分隔）", required = true) @RequestBody List<String> ids) {
        invoice6vatService.deleteInvoice6vatByIds(ids);
    }


    @Operation(summary ="删除增值税6%票")
    @DeleteMapping("/{id}")
    public void remove( @Parameter(description ="税票id", required = true) @PathVariable String id) {
        invoice6vatService.deleteInvoice6vatById(Long.valueOf(id));
    }

    @Operation(summary ="导出6%增值税专票列表")
    @PostMapping("/export")
    public void export(HttpServletRequest request, HttpServletResponse response,
            @Parameter(description ="需导出的发票id", required = true) @RequestBody List<Long> ids) {
        // 查询需导出的发票数据
        List<Invoice6VO> list = invoice6vatService.selectListByIds(ids);
        String fileName = ExcelUtil.encodingFileName(request, "增值税专票");
        ExcelUtil.export2WebWithTemplate(response, fileName,"增值税专票",
                fileApi.getTemplatePath() + File.separator + "增值税专票模板.xlsx", list);
    }

    @Operation(summary ="查询数据详情")
    @GetMapping("/info/{id}")
    public Invoice6vat details(@PathVariable Long id) {
        return invoice6vatService.selectById(id);
    }

    @Operation(summary ="导出所有6%增值税专票列表")
    @PostMapping("/export/all")
    public void export(HttpServletRequest request, HttpServletResponse response,
                       @RequestBody Invoice6vatReqVO cond,
                       @Parameter(hidden = true) @UserId Integer userId) {
        // 查询需导出的发票数据
        //通过用户登入信息得到用户名，赋值于导入人员
        cond.setUserId(userId);
        List<Invoice6VO> list = invoice6vatService.listInvoice6vatByCond(cond);
        String fileName = ExcelUtil.encodingFileName(request, "增值税专票");
        ExcelUtil.export2WebWithTemplate(response, fileName,"增值税专票",
                fileApi.getTemplatePath() + File.separator + "增值税专票模板.xlsx", list);
    }
}
