package com.hbhb.cw.invoice.service;

import com.hbhb.cw.invoice.web.vo.InvoiceInspectionExportVO;
import com.hbhb.cw.invoice.web.vo.InvoiceInspectionReqVO;
import com.hbhb.cw.invoice.web.vo.InvoiceInspectionResVO;
import com.hbhb.cw.invoice.web.vo.InvoiceInspectionVO;

import java.util.List;

/**
 * @author yzc
 * @since 2020-09-21
 */
public interface InvoiceInspectionService {

    /**
     * 获取增值税发票查验列表
     */
    InvoiceInspectionResVO getList(InvoiceInspectionReqVO cond, Integer pageNum, Integer pageSize);
    List<InvoiceInspectionVO> getList2(InvoiceInspectionReqVO cond);

    /**
     * 获取增值税发票查验导出列表
     */
    List<InvoiceInspectionExportVO> getExportList(InvoiceInspectionReqVO cond);
}
