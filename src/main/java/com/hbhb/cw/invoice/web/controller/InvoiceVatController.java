package com.hbhb.cw.invoice.web.controller;

import com.hbhb.core.utils.ExcelUtil;
import com.hbhb.cw.invoice.common.config.InvoiceErrorCode;
import com.hbhb.cw.invoice.common.exception.InvoiceException;
import com.hbhb.cw.invoice.model.InvoiceVat;
import com.hbhb.cw.invoice.model.Page;
import com.hbhb.cw.invoice.rpc.FileApiExp;
import com.hbhb.cw.invoice.service.InvoiceVatService;
import com.hbhb.cw.invoice.web.vo.InvoiceVatAddVO;
import com.hbhb.cw.invoice.web.vo.InvoiceVatReqVO;
import com.hbhb.cw.invoice.web.vo.InvoiceVatResVO;
import com.hbhb.cw.invoice.web.vo.InvoiceVatVO;
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
 * 增值税普票Controller
 *
 * @author ruoyi
 * @date 2020-06-01
 */
@Tag(name = "发票相关-增值税普票")
@RestController
@RequestMapping("/invoice_vat")
public class InvoiceVatController {

    @Resource
    private InvoiceVatService invoiceVatService;

    @Resource
    private FileApiExp fileApi;

    @Operation(summary ="查询增值税普票列表")
    @GetMapping("/list")
    public Page<InvoiceVatResVO> list(
            @Parameter(description = "页码，默认为1") @RequestParam(required = false) Integer pageNum,
            @Parameter(description = "每页数量，默认为10") @RequestParam(required = false)  Integer pageSize,
            InvoiceVatReqVO invoiceVatReqVO, @Parameter(hidden = true) @UserId Integer userId) {
        pageNum = pageNum == null ? 1 : pageNum;
        pageSize = pageSize == null ? 20 : pageSize;
        //通过用户登入信息得到用户名，赋值于导入人员
        invoiceVatReqVO.setUserId(userId);
        return invoiceVatService
                .selectInvoiceVatByCond(pageNum, pageSize, invoiceVatReqVO);
    }


    /**
     * 新增保存增值税普票
     */
    @Operation(summary ="新增保存增值税普票")
    @PostMapping("")
    public void addSave(@RequestBody InvoiceVatAddVO invoiceVatAddVO,
                        @Parameter(hidden = true) @UserId Integer userId) {
        InvoiceVat invoiceVat1 = invoiceVatService
                .selectInvoiceVatByNumber(invoiceVatAddVO);
        if (invoiceVat1 != null) {
            throw new InvoiceException(InvoiceErrorCode.INVOICE_ALREADY_EXISTS);
        }
        invoiceVatService.insertInvoiceVat(invoiceVatAddVO, userId);
    }

    /**
     * 修改保存增值税普票
     */
    @Operation(summary ="修改保存增值税普票")
    @PutMapping("")
    public void editSave(@RequestBody InvoiceVat invoiceVat) {
        invoiceVatService.updateInvoiceVat(invoiceVat);
    }

    /**
     * 批量删除增值税普票
     */
    @Operation(summary ="批量删除增值税普票")
    @DeleteMapping("/batch")
    public void removes(
            @Parameter(description ="税票id（逗号分隔）", required = true) @RequestBody List<String> ids) {
        invoiceVatService.deleteInvoiceVatByIds(ids);
    }

    /**
     * 删除增值税普票
     */
    @Operation(summary ="删除增值税普票")
    @DeleteMapping("/{id}")
    public void remove( @Parameter(description ="税票id", required = true) @PathVariable String id) {
        invoiceVatService.deleteInvoiceVatById(Long.valueOf(id));
    }


    @Operation(summary ="导出增值税普票列表")
    @PostMapping("/export")
    public void export(HttpServletRequest request, HttpServletResponse response,
                       @Parameter(description ="需导出的发票id", required = true) @RequestBody List<Long> ids) {
        // 查询需导出的发票数据
        List<InvoiceVatVO> list = invoiceVatService.selectListByIds(ids);
        String fileName = ExcelUtil.encodingFileName(request, "增值税普票");
        ExcelUtil.export2WebWithTemplate(response, fileName, "增值税普票",
                fileApi.getTemplatePath() + File.separator + "增值税普票模板.xlsx", list);
    }

    @Operation(summary ="查询数据详情")
    @GetMapping("/info/{id}")
    public InvoiceVat details(@PathVariable Long id) {
        return invoiceVatService.selectById(id);
    }

    @Operation(summary ="导出所有增值税普票列表")
    @PostMapping("/export/all")
    public void export(HttpServletRequest request, HttpServletResponse response,
                       @RequestBody InvoiceVatReqVO cond,
                       @Parameter(hidden = true) @UserId Integer userId) {
        //通过用户登入信息得到用户名，赋值于导入人员
        cond.setUserId(userId);
        List<InvoiceVatVO> list = invoiceVatService
                .listInvoiceVatByCond(cond);
        String fileName = ExcelUtil.encodingFileName(request, "增值税普票");
        ExcelUtil.export2WebWithTemplate(response, fileName, "增值税普票",
                fileApi.getTemplatePath() + File.separator + "增值税普票模板.xlsx", list);
    }
}
