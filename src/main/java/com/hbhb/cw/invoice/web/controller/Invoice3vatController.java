package com.hbhb.cw.invoice.web.controller;

import com.hbhb.core.utils.ExcelUtil;
import com.hbhb.cw.invoice.common.config.InvoiceErrorCode;
import com.hbhb.cw.invoice.common.exception.InvoiceException;
import com.hbhb.cw.invoice.model.Invoice3vat;
import com.hbhb.cw.invoice.service.Invoice3vatService;
import com.hbhb.cw.invoice.web.vo.Invoice3AddVO;
import com.hbhb.cw.invoice.web.vo.Invoice3ResVO;
import com.hbhb.cw.invoice.web.vo.Invoice3VO;
import com.hbhb.cw.invoice.web.vo.Invoice3vatReqVO;
import com.hbhb.springboot.web.view.Page;
import com.hbhb.web.annotation.UserId;

import org.springframework.beans.factory.annotation.Value;
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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Parameter;

/**
 * 3%增值税专票Controller
 *
 * @author ruoyi
 * @date 2020-06-02
 */
@Api(tags = "发票相关-3%增值税专票")
@RestController
@RequestMapping("/invoice/invoice_3vat")
public class Invoice3vatController {

    @Resource
    private Invoice3vatService invoice3vatService;

    @Value("${file.upload.template}")
    private String filePath;

    @ApiOperation("查询3%增值税专票列表")
    @GetMapping("/list")
    public Page<Invoice3ResVO> list(
            @ApiParam(value = "页码，默认为1") @RequestParam(required = false) Integer pageNum,
            @ApiParam(value = "每页数量，默认为10") @RequestParam(required = false) Integer pageSize,
            Invoice3vatReqVO invoice3vatReqVO, @Parameter(hidden = true) @UserId Integer userId) {
        pageNum = pageNum == null ? 1 : pageNum;
        pageSize = pageSize == null ? 20 : pageSize;
        //通过用户登入信息得到用户名，赋值于导入人员
        invoice3vatReqVO.setUserId(userId);
        return invoice3vatService
                .selectInvoice3vatByCond(pageNum, pageSize, invoice3vatReqVO);
    }


    @ApiOperation("新增保存3%增值税专票")
    @PostMapping("")
    public void addSave(@RequestBody Invoice3AddVO invoice3AddVO,
                        @Parameter(hidden = true) @UserId Integer userId) {
        Invoice3vat invoice3vat1 = invoice3vatService
                .selectInvoice3vatByNumber(invoice3AddVO);
        if (invoice3vat1 != null) {
            throw new InvoiceException(InvoiceErrorCode.INVOICE_ALREADY_EXISTS);
        }
        invoice3vatService.insertInvoice3vat(invoice3AddVO, userId);
    }

    @ApiOperation("修改保存3%增值税专票")
    @PutMapping("")
    public void editSave(@RequestBody Invoice3vat invoice3vat) {
        invoice3vatService.updateInvoice3vat(invoice3vat);
    }

    @ApiOperation("批量删除3%增值税专票")
    @DeleteMapping("/batch")
    public void removes(
            @ApiParam(value = "专票id数组", required = true) @RequestBody List<String> ids) {
        invoice3vatService.deleteInvoice3vatByIds(ids);
    }

    @ApiOperation("删除3%增值税票")
    @DeleteMapping("/{id}")
    public void remove(@ApiParam(value = "税票id", required = true) @PathVariable String id) {
        invoice3vatService.deleteInvoice3vatById(Long.valueOf(id));
    }

    @ApiOperation("导出3%增值税专票列表")
    @PostMapping("/export")
    public void export(HttpServletRequest request, HttpServletResponse response,
            @ApiParam(value = "需导出的发票id", required = true) @RequestBody List<Long> ids) {
        // 查询需导出的发票数据
        List<Invoice3VO> list = invoice3vatService.selectListByIds(ids);
        String fileName = ExcelUtil.encodingFileName(request, "增值税专票");
        ExcelUtil.export2WebWithTemplate(response, fileName,"增值税专票",
                filePath + File.separator + "增值税专票模板.xlsx", list);
    }

    @ApiOperation("查询数据详情")
    @GetMapping("/info/{id}")
    public Invoice3vat details(@PathVariable Long id) {
        return invoice3vatService.selectById(id);
    }

    @ApiOperation("导出所有3%增值税专票列表")
    @PostMapping("/export/all")
    public void export(HttpServletRequest request, HttpServletResponse response,
                       @ApiParam(value = "需导出的发票id", required = true) @RequestBody Invoice3vatReqVO cond,
                       @Parameter(hidden = true) @UserId Integer userId) {
        // 查询需导出的发票数据
        cond.setUserId(userId);
        List<Invoice3VO> list = invoice3vatService.listInvoice3vatByCond(cond);
        String fileName = ExcelUtil.encodingFileName(request, "增值税专票");
        ExcelUtil.export2WebWithTemplate(response, fileName,"增值税专票",
                filePath + File.separator + "增值税专票模板.xlsx", list);
    }
}
