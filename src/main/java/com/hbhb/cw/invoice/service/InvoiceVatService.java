package com.hbhb.cw.invoice.service;

import com.hbhb.cw.invoice.model.InvoiceVat;
import com.hbhb.cw.invoice.model.Page;
import com.hbhb.cw.invoice.web.vo.InvoiceVatAddVO;
import com.hbhb.cw.invoice.web.vo.InvoiceVatReqVO;
import com.hbhb.cw.invoice.web.vo.InvoiceVatResVO;
import com.hbhb.cw.invoice.web.vo.InvoiceVatVO;

import java.util.List;

/**
 * 增值税普票Service接口
 *
 * @author ruoyi
 * @date 2020-06-01
 */
public interface InvoiceVatService {

    List<InvoiceVatVO> listInvoiceVatByCond(InvoiceVatReqVO invoiceVatReqVO);

    /**
     * 查询增值税普票
     *
     * @param vatId 增值税普票ID
     * @return 增值税普票
     */
    public InvoiceVat selectInvoiceVatById(Long vatId);


    /**
     * 通过id数组查询
     */
    List<InvoiceVatVO> selectListByIds(List<Long> ids);

    /**
     * 通过条件查询增值税普票列表
     */
    public Page<InvoiceVatResVO> selectInvoiceVatByCond(Integer pageNum, Integer pageSize,
            InvoiceVatReqVO invoiceVatReqVO);

    /**
     * 通过发票号码查找发票
     */
    public InvoiceVat selectInvoiceVatByNumber(InvoiceVatAddVO invoiceVatAddVO);

    /**
     * 新增增值税普票
     *
     * @return 结果
     */
    public void insertInvoiceVat(InvoiceVatAddVO invoiceVatAddVO, Integer userId);

    /**
     * 修改增值税普票
     *
     * @param invoiceVat 增值税普票
     * @return 结果
     */
    public int updateInvoiceVat(InvoiceVat invoiceVat);

    /**
     * 批量删除增值税普票
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteInvoiceVatByIds(List<String> ids);

    /**
     * 删除增值税普票信息
     *
     * @param vatId 增值税普票ID
     * @return 结果
     */
    public int deleteInvoiceVatById(Long vatId);

    /**
     * 通过id查询
     * @param id
     * @return
     */
    InvoiceVat selectById(Long id);

}
