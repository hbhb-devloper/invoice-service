package com.hbhb.cw.invoice.service;

import com.hbhb.cw.invoice.model.Page;
import com.hbhb.cw.invoice.web.vo.InvoiceAccountRunExportVO;
import com.hbhb.cw.invoice.web.vo.InvoiceAccountRunImportVO;
import com.hbhb.cw.invoice.web.vo.InvoiceAccountRunResVO;
import com.hbhb.cw.invoice.web.vo.InvoiceAccountRunVO;
import com.hbhb.cw.invoice.web.vo.InvoiceContrastReqVO;
import com.hbhb.cw.invoice.web.vo.InvoiceContrastVO;

import java.util.List;

/**
 * @author yzc
 * @since 2020-10-19
 */
public interface InvoiceAccountRunService {

    /**
     * 按条件查询往来账管理（分页）
     */
    Page<InvoiceAccountRunResVO> getPageByCont(InvoiceAccountRunVO cond, Integer userId, Integer pageNum, Integer pageSize);

    /**
     * 获取导出列表所需数据
     */
    List<InvoiceAccountRunExportVO> getListByCont(InvoiceAccountRunVO cond, Integer userId);

    /**
     * 批量导入保存往来账管理
     */
    void saveInvoiceAccountRun(List<InvoiceAccountRunImportVO> dataList, Integer unitId);

    /**
     * 获取往来账导入时间
     */
    String getTime(int unitId);

    /**
     * 通过条件获取对比表数据
     */
    List<InvoiceContrastVO> getContrastByCond(InvoiceContrastReqVO cond);

    /**
     * 通过条件获取对比表数据条数
     */
    int getContrastCountByCond(InvoiceContrastReqVO cond);

    List<String> getMsg();
}
