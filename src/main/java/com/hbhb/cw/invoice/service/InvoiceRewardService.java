package com.hbhb.cw.invoice.service;

import com.hbhb.cw.invoice.web.vo.InvoiceAccountImportVO;
import com.hbhb.cw.invoice.web.vo.InvoiceCheckExportVO;
import com.hbhb.cw.invoice.web.vo.InvoiceDetailsExportVO;
import com.hbhb.cw.invoice.web.vo.InvoiceMonthExportVO;
import com.hbhb.cw.invoice.web.vo.InvoiceRewardImportVO;
import com.hbhb.cw.invoice.web.vo.InvoiceRewardReqVO;
import com.hbhb.cw.invoice.web.vo.InvoiceRewardResVO;
import com.hbhb.cw.invoice.web.vo.InvoiceSubsidyImportVO;
import com.hbhb.springboot.web.view.Page;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author yzc
 * @since 2020-09-09
 */
public interface InvoiceRewardService {


    /**
     * 按条件查询酬金列表（用于删除）
     */
    Page<InvoiceRewardResVO> getListByCont(InvoiceRewardReqVO cond, Integer pageNum, Integer pageSize);

    /**
     * 批量保存酬金发放数据
     */
    void saveInvoiceDetails(List<InvoiceRewardImportVO> dataList, String importDate, Integer unitId, BigDecimal taxRate);

    /**
     * 综合补贴表
     */
    void saveInvoiceSubsidy(List<InvoiceSubsidyImportVO> dataList, String importDate, Integer unitId);

    /**
     * 往来账导入
     */
    void saveInvoiceAccount(List<InvoiceAccountImportVO> dataList, Integer unitId);

    /**
     * 支付明细导出
     */
    List<InvoiceDetailsExportVO> getDetailsExportByCond(InvoiceRewardReqVO cond);

    /**
     * 酬金月份导出
     */
    List<InvoiceMonthExportVO> getMonthExportByCond(InvoiceRewardReqVO cond);

    /**
     * 查验结果导出
     */
    List<InvoiceCheckExportVO> getCheckExportByCond(InvoiceRewardReqVO cond);

    /**
     * 得到导excel时出错的数据
     */
    List<String> getMsg();

    /**
     * 通过流水号删除
     */
    void deleteBySerialNumber(List<String> list);

}
