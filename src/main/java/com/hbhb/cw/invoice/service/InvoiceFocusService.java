package com.hbhb.cw.invoice.service;

import com.hbhb.cw.invoice.web.vo.InvoiceFocusAccountResVO;
import com.hbhb.cw.invoice.web.vo.InvoiceFocusImportVO;
import com.hbhb.cw.invoice.web.vo.InvoiceFocusVO;
import com.hbhb.springboot.web.view.Page;

import java.util.List;

/**
 * @author yzc
 * @since 2020-10-21
 */
public interface InvoiceFocusService {

    /**
     * 按条件查询集中化应付台账管理（分页）
     */
    Page<InvoiceFocusAccountResVO> getPageByCont(InvoiceFocusVO cond, Integer pageNum, Integer pageSize);

    /**
     * 批量保存导入集中化应付台账管理
     */
    void saveInvoiceFocus(List<InvoiceFocusImportVO> dataList, Integer unitId);

    List<String> getMsg();
}
