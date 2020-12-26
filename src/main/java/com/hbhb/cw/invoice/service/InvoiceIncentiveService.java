package com.hbhb.cw.invoice.service;

import com.hbhb.cw.invoice.web.vo.InvoiceIncentiveImportVO;
import com.hbhb.cw.invoice.web.vo.InvoiceIncentiveResVO;
import com.hbhb.cw.invoice.web.vo.InvoiceIncentiveVO;
import com.hbhb.springboot.web.view.Page;

import java.util.List;

/**
 * @author yzc
 * @since 2020-10-20
 */
public interface InvoiceIncentiveService {

    /**
     * 按条件查询综合激励表（分页）
     */
    Page<InvoiceIncentiveResVO> getPageByCont(InvoiceIncentiveVO cond, Integer pageNum, Integer pageSize);

    /**
     * 批量导入保存综合激励表
     */
    void saveInvoiceIncentive(List<InvoiceIncentiveImportVO> dataList, Integer unitId);

    /**
     * 得到msg
     */
    List<String> getMsg();
}
