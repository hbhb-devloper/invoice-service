package com.hbhb.cw.invoice.web.controller;

import com.alibaba.excel.EasyExcel;
import com.hbhb.core.utils.DateUtil;
import com.hbhb.core.utils.ExcelUtil;
import com.hbhb.cw.invoice.common.config.InvoiceErrorCode;
import com.hbhb.cw.invoice.common.exception.InvoiceException;
import com.hbhb.cw.invoice.model.Page;
import com.hbhb.cw.invoice.rpc.FileApiExp;
import com.hbhb.cw.invoice.service.InvoiceRewardService;
import com.hbhb.cw.invoice.service.listener.InvoiceCurrentAccountListener;
import com.hbhb.cw.invoice.service.listener.InvoiceDetailsListener;
import com.hbhb.cw.invoice.service.listener.InvoiceSubsidyListener;
import com.hbhb.cw.invoice.web.vo.InvoiceAccountImportVO;
import com.hbhb.cw.invoice.web.vo.InvoiceCheckExportVO;
import com.hbhb.cw.invoice.web.vo.InvoiceDetailsExportVO;
import com.hbhb.cw.invoice.web.vo.InvoiceMonthExportVO;
import com.hbhb.cw.invoice.web.vo.InvoiceRewardImportVO;
import com.hbhb.cw.invoice.web.vo.InvoiceRewardReqVO;
import com.hbhb.cw.invoice.web.vo.InvoiceRewardResVO;
import com.hbhb.cw.invoice.web.vo.InvoiceSubsidyImportVO;
import com.hbhb.web.annotation.UserId;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yzc
 * @since 2020-09-08
 */
@Tag(name="????????????-????????????")
@RestController
@RequestMapping("/remuneration")
@Slf4j
public class InvoiceRewardController {

    @Resource
    private FileApiExp fileApi;

    @Resource
    private InvoiceRewardService invoiceRewardService;


    @Operation(summary = "?????????????????????????????????")
    @GetMapping("/list")
    public Page<InvoiceRewardResVO> getDetails(
            @Parameter(description = "??????") InvoiceRewardReqVO cond,
           @Parameter(description = "??????????????????1") @RequestParam(required = false) Integer pageNum,
           @Parameter(description = "????????????????????????10") @RequestParam(required = false) Integer pageSize,
            @Parameter(hidden = true) @UserId Integer userId) {
        if (cond.getImportDate() == null) {
            cond.setImportDate(DateUtil.getLastMonth());
        }
        pageNum = pageNum == null ? 1 : pageNum;
        pageSize = pageSize == null ? 20 : pageSize;
        return invoiceRewardService.getListByCont(cond, userId, pageNum, pageSize);
    }

    @Operation(summary ="?????????????????????")
    @PostMapping("/import/reward")
    public List<String> importDetailsBreak(MultipartFile file, BigDecimal taxRate,
                                           @Parameter(hidden = true) @UserId Integer userId) {
        log.info("controller???????????????");
        StringBuffer importDate = new StringBuffer(DateUtil.getLastMonth());
        long begin = System.currentTimeMillis();
        try {
            EasyExcel.read(file.getInputStream(), InvoiceRewardImportVO.class,
                    new InvoiceDetailsListener(invoiceRewardService, importDate, new AtomicInteger(userId), taxRate)).sheet()
                    .headRowNumber(1).doRead();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new InvoiceException(InvoiceErrorCode.INVOICE_DATA_IMPORT_ERROR);
        }
        log.info("?????????");
        log.info("?????????????????????????????????????????????" + (System.currentTimeMillis() - begin) / 1000 + "s");
        return invoiceRewardService.getMsg();
    }

    @Operation(summary ="?????????????????????")
    @PostMapping("/import/subsidy")
    public List<String> importSubsidy(MultipartFile file, @Parameter(hidden = true) @UserId Integer userId) {
        long begin = System.currentTimeMillis();
        StringBuffer importDate = new StringBuffer(DateUtil.getLastMonth());
        try {
            EasyExcel.read(file.getInputStream(), InvoiceSubsidyImportVO.class,
                    new InvoiceSubsidyListener(invoiceRewardService, importDate, new AtomicInteger(userId))).sheet()
                    .headRowNumber(2).doRead();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new InvoiceException(InvoiceErrorCode.INVOICE_DATA_IMPORT_ERROR);
        }
        log.info("?????????????????????????????????????????????" + (System.currentTimeMillis() - begin) / 1000 + "s");
        return invoiceRewardService.getMsg();
    }

    @Operation(summary ="??????????????????")
    @PostMapping("/import/account")
    public List<String> importAccount(MultipartFile file, @Parameter(hidden = true) @UserId Integer userId) {
        long begin = System.currentTimeMillis();
        try {
            EasyExcel.read(file.getInputStream(), InvoiceAccountImportVO.class,
                    new InvoiceCurrentAccountListener(invoiceRewardService, new AtomicInteger(userId))).sheet()
                    .headRowNumber(2).doRead();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new InvoiceException(InvoiceErrorCode.INVOICE_DATA_IMPORT_ERROR);
        }
        log.info("??????????????????????????????????????????" + (System.currentTimeMillis() - begin) / 1000 + "s");
        return invoiceRewardService.getMsg();
    }

    @Operation(summary ="???????????????????????????")
    @PostMapping("/export/reward/template")
    public void rewardTemplate(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<Object> list = new ArrayList<>();
        String fileName = ExcelUtil.encodingFileName(request, "?????????????????????");
        ExcelUtil.export2WebWithTemplate(response, fileName, "?????????????????????",
                fileApi.getTemplatePath() + File.separator + "?????????????????????.xlsx", list);
    }

    @Operation(summary ="????????????????????????")
    @PostMapping("/export/subsidy/template")
    public void subsidyTemplate(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<Object> list = new ArrayList<>();
        String fileName = ExcelUtil.encodingFileName(request, "??????????????????");
        ExcelUtil.export2WebWithTemplate(response, fileName, "????????????",
                fileApi.getTemplatePath() + File.separator + "??????????????????.xlsx", list);
    }

    @Operation(summary ="?????????????????????")
    @PostMapping("/export/account/template")
    public void accountTemplate(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<Object> list = new ArrayList<>();
        String fileName = ExcelUtil.encodingFileName(request, "???????????????");
        ExcelUtil.export2WebWithTemplate(response, fileName, "?????????",
                fileApi.getTemplatePath() + File.separator + "???????????????.xlsx", list);
    }

    @Operation(summary ="??????????????????")
    @PostMapping("/export/subsidy")
    public void exportSubsidy(HttpServletRequest request, HttpServletResponse response,
                              @Parameter(description = "??????") @RequestBody InvoiceRewardReqVO cond,
                              @Parameter(hidden = true) @UserId Integer userId) {
        if (cond.getImportDate() == null) {
            cond.setImportDate(DateUtil.getLastMonth());
        }
        List<InvoiceDetailsExportVO> list = invoiceRewardService.getDetailsExportByCond(cond,userId);
        String fileName = ExcelUtil.encodingFileName(request, "?????????????????????");
        ExcelUtil.export2WebWithTemplate(response, fileName, "????????????",
                fileApi.getTemplatePath() + File.separator + "?????????????????????.xlsx", list);
    }


    @Operation(summary ="??????????????????")
    @PostMapping("/export/month")
    public void exportByMonth(HttpServletRequest request, HttpServletResponse response,
                              @Parameter(description = "??????") @RequestBody InvoiceRewardReqVO cond,
                              @Parameter(hidden = true) @UserId Integer userId) {
        if (cond.getImportDate() == null) {
            cond.setImportDate(DateUtil.getLastMonth());
        }
        List<InvoiceMonthExportVO> list = invoiceRewardService.getMonthExportByCond(cond,userId);
        String fileName = ExcelUtil.encodingFileName(request, "?????????????????????");
        ExcelUtil.export2WebWithTemplate(response, fileName, "???????????????????????????",
                fileApi.getTemplatePath() + File.separator + "?????????????????????.xlsx", list);
    }


    @Operation(summary ="??????????????????")
    @PostMapping("/export/check")
    public void exportCheck(HttpServletRequest request, HttpServletResponse response,
                            @Parameter(description= "??????") @RequestBody InvoiceRewardReqVO cond,
                            @Parameter(hidden = true) @UserId Integer userId) {
        if (cond.getImportDate() == null) {
            cond.setImportDate(DateUtil.getLastMonth());
        }
        List<InvoiceCheckExportVO> list = invoiceRewardService.getCheckExportByCond(cond,userId);
        String fileName = ExcelUtil.encodingFileName(request, "?????????????????????");
        ExcelUtil.export2WebWithTemplate(response, fileName, "????????????????????????",
                fileApi.getTemplatePath() + File.separator + "?????????????????????.xlsx", list);
    }


    @Operation(summary ="?????????????????????????????????")
    @DeleteMapping("")
    public void removeBySerialNumber(@RequestBody List<String> list) {
        invoiceRewardService.deleteBySerialNumber(list);
    }
}
