package com.hbhb.cw.invoice.service;

import com.hbhb.cw.invoice.model.Invoice3vat;
import com.hbhb.cw.invoice.web.vo.Invoice3AddVO;
import com.hbhb.cw.invoice.web.vo.Invoice3ResVO;
import com.hbhb.cw.invoice.web.vo.Invoice3VO;
import com.hbhb.cw.invoice.web.vo.Invoice3vatReqVO;
import com.hbhb.springboot.web.view.Page;

import java.util.List;

/**
 * 3%增值税专票Service接口
 *
 * @author ruoyi
 * @date 2020-06-02
 */
public interface Invoice3vatService {

    List<Invoice3VO> listInvoice3vatByCond(Invoice3vatReqVO cond);

    /**
     * 查询3%增值税专票
     *
     * @param vat3Id 3%增值税专票ID
     * @return 3%增值税专票
     */
    public Invoice3vat selectInvoice3vatById(Long vat3Id);

    /**
     * 通过发票号码查询
     */
    public Invoice3vat selectInvoice3vatByNumber(Invoice3AddVO invoice3AddVO);


    /**
     * 通过条件查询3%增值税专票列表
     */
    public Page<Invoice3ResVO> selectInvoice3vatByCond(Integer pageNum, Integer pageSize,
            Invoice3vatReqVO invoice3vatReqVO);


    /**
     * 新增3%增值税专票
     *
     * @return 结果
     */
    public void insertInvoice3vat(Invoice3AddVO invoice3AddVO, Integer userId);

    /**
     * 修改3%增值税专票
     *
     * @param invoice3vat 3%增值税专票
     * @return 结果
     */
    public int updateInvoice3vat(Invoice3vat invoice3vat);

    /**
     * 通过id数组查询
     */
    List<Invoice3VO> selectListByIds(List<Long> ids);

    /**
     * 批量删除3%增值税专票
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteInvoice3vatByIds(List<String> ids);

    /**
     * 删除3%增值税专票信息
     *
     * @param vat3Id 3%增值税专票ID
     * @return 结果
     */
    public int deleteInvoice3vatById(Long vat3Id);

    /**
     * 通过id查询
     * @param vat3Id
     * @return
     */
    Invoice3vat selectById(Long vat3Id);
}
