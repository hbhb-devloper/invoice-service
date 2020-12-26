package com.hbhb.cw.invoice.service;

import com.hbhb.cw.invoice.web.vo.InvoiceContrastExportVO;
import com.hbhb.cw.invoice.web.vo.InvoiceContrastReqVO;
import com.hbhb.cw.invoice.web.vo.InvoiceContrastResVO;

import java.util.List;

/**
 * @author yzc
 * @since 2020-10-22
 */
public interface InvoiceContrastService {

    /**
     * 按条件查询集中化应付台账管理（分页）
     */
    InvoiceContrastResVO getPageByCont(InvoiceContrastReqVO cond, Integer pageNum, Integer pageSize);

    /**
     * 按条件查询集中化应付台账管理（不分页）
     */
    List<InvoiceContrastExportVO> getExportByCont(InvoiceContrastReqVO cond);
}
