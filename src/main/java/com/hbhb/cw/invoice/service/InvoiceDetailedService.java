package com.hbhb.cw.invoice.service;

import com.hbhb.cw.invoice.model.Page;
import com.hbhb.cw.invoice.web.vo.InvoiceDetailedExportVO;
import com.hbhb.cw.invoice.web.vo.InvoiceDetailedImportVO;
import com.hbhb.cw.invoice.web.vo.InvoiceDetailedVO;
import com.hbhb.cw.invoice.web.vo.InvoiceRewardDetailedResVO;

import java.util.List;

/**
 * @author yzc
 * @since 2020-10-21
 */
public interface InvoiceDetailedService {

    /**
     * 按条件查询酬金计提明细管理表（分页）
     */
    Page<InvoiceRewardDetailedResVO> getPageByCont(InvoiceDetailedVO cond, Integer userId, Integer pageNum, Integer pageSize);

    /**
     * 批量导入酬金计提明细管理表
     */
    void saveInvoiceDetailed(List<InvoiceDetailedImportVO> dataList, Integer unitId);

    /**
     * 获取导出列表所需数据
     */
    List<InvoiceDetailedExportVO> getListByCont(InvoiceDetailedVO cond, Integer userId);

    List<String> getMsg();
}
