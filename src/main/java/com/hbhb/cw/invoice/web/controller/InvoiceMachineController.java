package com.hbhb.cw.invoice.web.controller;

import com.hbhb.core.utils.ExcelUtil;
import com.hbhb.cw.invoice.common.config.InvoiceErrorCode;
import com.hbhb.cw.invoice.common.exception.InvoiceException;
import com.hbhb.cw.invoice.model.InvoiceMachine;
import com.hbhb.cw.invoice.model.Page;
import com.hbhb.cw.invoice.rpc.FileApiExp;
import com.hbhb.cw.invoice.service.InvoiceMachineService;
import com.hbhb.cw.invoice.web.vo.InvoiceMachineAddVO;
import com.hbhb.cw.invoice.web.vo.InvoiceMachineReqVO;
import com.hbhb.cw.invoice.web.vo.InvoiceMachineResVO;
import com.hbhb.cw.invoice.web.vo.InvoiceMachineVO;
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
 * 通用机打发票Controller
 *
 * @author ruoyi
 * @date 2020-06-02
 */
@Tag(name = "发票相关-通用机打发票")
@RestController
@RequestMapping("/invoice_machine")
public class InvoiceMachineController {

    @Resource
    private InvoiceMachineService invoiceMachineService;

    @Resource
    private FileApiExp fileApi;

    @Operation(summary ="查询通用机打发票列表")
    @GetMapping("/list")
    public Page<InvoiceMachineResVO> list(
           @Parameter(description = "页码，默认为1") @RequestParam(required = false) Integer pageNum,
           @Parameter(description = "每页数量，默认为10") @RequestParam(required = false) Integer pageSize,
            InvoiceMachineReqVO invoiceMachineReqVO, @Parameter(hidden = true) @UserId Integer userId) {
        pageNum = pageNum == null ? 1 : pageNum;
        pageSize = pageSize == null ? 20 : pageSize;
        invoiceMachineReqVO.setUserId(userId);
        return invoiceMachineService
                .selectInvoiceMachineByCond(pageNum, pageSize, invoiceMachineReqVO);
    }

    @Operation(summary ="新增保存通用机打发票")
    @PostMapping("/add")
    public void addSave(@RequestBody InvoiceMachineAddVO invoiceMachineAddVO,
                        @Parameter(hidden = true) @UserId Integer userId) {
        InvoiceMachine invoiceMachine1 = invoiceMachineService
                .selectInvoiceMachineByNumber(invoiceMachineAddVO);
        if (invoiceMachine1 != null) {
            throw new InvoiceException(InvoiceErrorCode.INVOICE_ALREADY_EXISTS);
        }
        invoiceMachineService.insertInvoiceMachine(invoiceMachineAddVO, userId);
    }

    @Operation(summary ="修改保存通用机打发票")
    @PutMapping("/edit")
    public void editSave(@RequestBody InvoiceMachine invoiceMachine) {
        invoiceMachineService.updateInvoiceMachine(invoiceMachine);
    }

    @Operation(summary ="批量删除通用机打发票")
    @DeleteMapping("/batch")
    public void removes(
            @Parameter(description ="税票id（逗号分隔）", required = true) @RequestBody List<String> ids) {
        invoiceMachineService.deleteInvoiceMachineByIds(ids);
    }


    @Operation(summary ="删除通用机打发票")
    @DeleteMapping("/{id}")
    public void remove( @Parameter(description = "税票id", required = true) @PathVariable String id) {
        invoiceMachineService.deleteInvoiceMachineById(Long.valueOf(id));
    }

    @Operation(summary ="导出通用机打发票列表")
    @PostMapping("/export")
    public void export(HttpServletRequest request, HttpServletResponse response,
                       @Parameter(description ="需导出的发票id", required = true) @RequestBody List<Long> ids) {
        String file = fileApi.getTemplatePath() + File.separator + "通用发票导入模板.xlsx";
        // 查询需导出的发票数据
        List<InvoiceMachineVO> list = invoiceMachineService.selectListByIds(ids);
        String fileName = ExcelUtil.encodingFileName(request, "通用发票");
        ExcelUtil.export2WebWithTemplate(response, fileName, "Sheet1", file, list);
    }

    @Operation(summary ="查询数据详情")
    @GetMapping("/info/{id}")
    public InvoiceMachine details(@PathVariable Long id) {
        return invoiceMachineService.selectById(id);
    }

    @Operation(summary ="导出通用机打发票列表")
    @PostMapping("/export/all")
    public void export(HttpServletRequest request, HttpServletResponse response,
                       @RequestBody InvoiceMachineReqVO cond,
                       @Parameter(hidden = true) @UserId Integer userId) {
        String file = fileApi.getTemplatePath() + File.separator + "通用发票导入模板.xlsx";
        //通过用户登入信息得到用户名，赋值于导入人员
        cond.setUserId(userId);
        List<InvoiceMachineVO> list = invoiceMachineService
                .listInvoiceMachineByCond(cond);
        String fileName = ExcelUtil.encodingFileName(request, "通用发票");
        ExcelUtil.export2WebWithTemplate(response, fileName, "Sheet1", file, list);
    }
}
