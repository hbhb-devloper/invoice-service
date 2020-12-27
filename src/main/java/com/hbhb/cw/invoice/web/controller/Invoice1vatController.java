package com.hbhb.cw.invoice.web.controller;

import com.hbhb.core.utils.ExcelUtil;
import com.hbhb.cw.invoice.common.config.InvoiceErrorCode;
import com.hbhb.cw.invoice.common.exception.InvoiceException;
import com.hbhb.cw.invoice.model.Invoice1vat;
import com.hbhb.cw.invoice.model.Page;
import com.hbhb.cw.invoice.rpc.FileApiExp;
import com.hbhb.cw.invoice.service.Invoice1vatService;
import com.hbhb.cw.invoice.web.vo.Invoice1AddVO;
import com.hbhb.cw.invoice.web.vo.Invoice1ResVO;
import com.hbhb.cw.invoice.web.vo.Invoice1VO;
import com.hbhb.cw.invoice.web.vo.Invoice1vatReqVO;
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
 * 1%增值税专票Controller
 *
 * @author ruoyi
 * @date 2020-06-01
 */
@Tag(name = "发票相关-1%增值税专票")
@RestController
@RequestMapping("/invoice_1vat")
public class Invoice1vatController {

    @Resource
    private Invoice1vatService invoice1vatService;

    @Resource
    private FileApiExp fileApi;

    @Operation(summary ="查询1%增值税专票列表")
    @GetMapping("/list")
    public Page<Invoice1ResVO> list(
           @Parameter(description = "页码，默认为1") @RequestParam(required = false) Integer pageNum,
           @Parameter(description = "每页数量，默认为10") @RequestParam(required = false) Integer pageSize,
            Invoice1vatReqVO cond, @Parameter(hidden = true) @UserId Integer userId) {
        pageNum = pageNum == null ? 1 : pageNum;
        pageSize = pageSize == null ? 20 : pageSize;
        //通过用户登入信息得到用户名，赋值于导入人员
        cond.setUserId(userId);
        return invoice1vatService
                .pageInvoice1vatByCond(pageNum, pageSize, cond);
    }

    @Operation(summary ="新增1%增值税专票")
    @PostMapping("")
    public void saveInvoice1vat(@RequestBody Invoice1AddVO invoice1AddVO,
                                @Parameter(hidden = true) @UserId Integer userId) {
        Invoice1vat invoice1vat1 = invoice1vatService
                .selectInvoice1vatByNumber(invoice1AddVO);
        if (invoice1vat1 != null) {
            throw new InvoiceException(InvoiceErrorCode.INVOICE_ALREADY_EXISTS);
        }
        invoice1vatService.insertInvoice1vat(invoice1AddVO, userId);
    }


    @Operation(summary ="修改1%增值税专票")
    @PutMapping("")
    public void updateInvoice1vat(@RequestBody Invoice1vat invoice1vat) {
        invoice1vatService.updateInvoice1vat(invoice1vat);
    }

    @Operation(summary ="批量删除1%增值税专票")
    @DeleteMapping("/batch")
    public void removes(
            @Parameter(description = "专票id数组", required = true) @RequestBody List<String> ids) {
        invoice1vatService.deleteInvoice1vatByIds(ids);
    }

    @Operation(summary ="删除6%增值税票")
    @DeleteMapping("/{id}")
    public void remove(
            @Parameter(description ="税票id", required = true) @PathVariable String id) {
        invoice1vatService.deleteInvoice1vatById(Long.valueOf(id));
    }

    @Operation(summary ="导出1%增值税专票列表")
    @PostMapping("/export")
    public void export(HttpServletRequest request, HttpServletResponse response,
                       @Parameter(description = "需导出的发票id", required = true) @RequestBody List<Long> ids) {
        List<Invoice1VO> list = invoice1vatService.selectListByIds(ids);
        String fileName = ExcelUtil.encodingFileName(request, "增值税专票");
        String templatePath = fileApi.getTemplatePath();
        ExcelUtil.export2WebWithTemplate(response, fileName,"增值税专票",
                templatePath + File.separator + "增值税专票模板.xlsx", list);
    }

    @Operation(summary ="查询数据详情")
    @GetMapping("/info/{id}")
    public Invoice1vat details(@PathVariable Long id) {
        return invoice1vatService.selectById(id);
    }

    @Operation(summary ="导出所有1%增值税专票列表")
    @PostMapping("/export/all")
    public void templateWrite(HttpServletRequest request, HttpServletResponse response,
                              @Parameter(description = "需导出的发票id", required = true) @RequestBody Invoice1vatReqVO cond,
                              @Parameter(hidden = true) @UserId Integer userId) {
        // 通过用户登入信息得到用户名，赋值于导入人员
        cond.setUserId(userId);
        List<Invoice1VO> list = invoice1vatService.listInvoice1vatByCond(cond);
        String fileName = ExcelUtil.encodingFileName(request, "增值税专票");
        String templatePath = fileApi.getTemplatePath();
        ExcelUtil.export2WebWithTemplate(response, fileName,"增值税专票",
                templatePath + File.separator + "增值税专票模板.xlsx", list);
    }
}
