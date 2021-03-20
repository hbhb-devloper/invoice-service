package com.hbhb.cw.invoice.service;

import com.hbhb.cw.invoice.model.Invoice1vat;
import com.hbhb.cw.invoice.model.Page;
import com.hbhb.cw.invoice.web.vo.Invoice1AddVO;
import com.hbhb.cw.invoice.web.vo.Invoice1ResVO;
import com.hbhb.cw.invoice.web.vo.Invoice1VO;
import com.hbhb.cw.invoice.web.vo.Invoice1vatReqVO;
import com.hbhb.cw.invoice.web.vo.InvoiceInspectResVat;
import com.hbhb.cw.invoice.web.vo.InvoiceInspectionReqVO;

import java.util.List;

/**
 * 1%增值税专票Service接口
 */
public interface Invoice1vatService {

    Page<Invoice1ResVO> pageInvoice1vatByCond(Integer pageNum, Integer pageSize,
                                              Invoice1vatReqVO cond);

    List<Invoice1VO> listInvoice1vatByCond(Invoice1vatReqVO cond);

    List<Invoice1VO> selectListByIds(List<Long> ids);

    Invoice1vat selectInvoice1vatByNumber(Invoice1AddVO invoice1AddVO);

    void insertInvoice1vat(Invoice1AddVO invoice1AddVO, Integer userId);

    void updateInvoice1vat(Invoice1vat invoice1vat);

    void deleteInvoice1vatByIds(List<String> ids);

    void deleteInvoice1vatById(Long id);

    /**
     * 通过id查询
     * @param vat1Id
     * @return
     */
    Invoice1vat selectById(Long vat1Id);

    /**
     * 通过条件得到百分之一发票的列表
     */
    List<InvoiceInspectResVat> getListByCond(InvoiceInspectionReqVO cond);
}
