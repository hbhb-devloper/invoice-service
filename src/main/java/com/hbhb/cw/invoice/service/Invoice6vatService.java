package com.hbhb.cw.invoice.service;

import com.hbhb.cw.invoice.model.Invoice6vat;
import com.hbhb.cw.invoice.web.vo.Invoice6AddVO;
import com.hbhb.cw.invoice.web.vo.Invoice6ResVO;
import com.hbhb.cw.invoice.web.vo.Invoice6VO;
import com.hbhb.cw.invoice.web.vo.Invoice6vatReqVO;
import com.hbhb.springboot.web.view.Page;

import java.util.List;

/**
 * 6%增值税专票Service接口
 *
 * @author ruoyi
 * @date 2020-06-02
 */
public interface Invoice6vatService {

    List<Invoice6VO> listInvoice6vatByCond(Invoice6vatReqVO cond);

    /**
     * 查询6%增值税专票
     *
     * @param vat6Id 6%增值税专票ID
     * @return 6%增值税专票
     */
    public Invoice6vat selectInvoice6vatById(Long vat6Id);



    /**
     * 通过条件查询6%增值税专票列表
     */
    public Page<Invoice6ResVO> selectInvoice6vatByCond(Integer pageNum, Integer pageSize,
            Invoice6vatReqVO invoice6vatReqVO);


    /**
     * 通过id数组查询
     */
    List<Invoice6VO> selectListByIds(List<Long> ids);

    /**
     * 新增6%增值税专票
     *
     * @return 结果
     */
    public void insertInvoice6vat(Invoice6AddVO invoice6AddVO, Integer userId);

    /**
     * 修改6%增值税专票
     *
     * @param invoice6vat 6%增值税专票
     * @return 结果
     */
    public int updateInvoice6vat(Invoice6vat invoice6vat);

    /**
     * 批量删除6%增值税专票
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteInvoice6vatByIds(List<String> ids);

    /**
     * 删除6%增值税专票信息
     *
     * @param vat6Id 6%增值税专票ID
     * @return 结果
     */
    public int deleteInvoice6vatById(Long vat6Id);

    /**
     * 通过发票号码查询6%增值税专票
     */
    public Invoice6vat selectInvoice6vatByNumber(Invoice6AddVO invoice6AddVO);

    /**
     * 通过id查询
     * @param vat6Id
     * @return
     */
    Invoice6vat selectById(Long vat6Id);

}
