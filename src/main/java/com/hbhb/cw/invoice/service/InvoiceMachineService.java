package com.hbhb.cw.invoice.service;

import com.hbhb.cw.invoice.model.InvoiceMachine;
import com.hbhb.cw.invoice.web.vo.InvoiceMachineAddVO;
import com.hbhb.cw.invoice.web.vo.InvoiceMachineReqVO;
import com.hbhb.cw.invoice.web.vo.InvoiceMachineResVO;
import com.hbhb.cw.invoice.web.vo.InvoiceMachineVO;
import com.hbhb.springboot.web.view.Page;

import java.util.List;

/**
 * 通用机打发票Service接口
 *
 * @author ruoyi
 * @date 2020-06-02
 */
public interface InvoiceMachineService {

    List<InvoiceMachineVO> listInvoiceMachineByCond(InvoiceMachineReqVO invoiceMachineReqVO);

    /**
     * 查询通用机打发票
     *
     * @param machineInvoiceId 通用机打发票ID
     * @return 通用机打发票
     */
    public InvoiceMachine selectInvoiceMachineById(Long machineInvoiceId);


    /**
     * 通过条件查询通用机打发票列表
     */
    public Page<InvoiceMachineResVO> selectInvoiceMachineByCond(Integer pageNum, Integer pageSize,
            InvoiceMachineReqVO invoiceMachineReqVO);

    /**
     * 通过id数组查询
     */
    List<InvoiceMachineVO> selectListByIds(List<Long> ids);


    /**
     * 新增通用机打发票
     *
     * @return 结果
     */
    public void insertInvoiceMachine(InvoiceMachineAddVO invoiceMachineAddVO, Integer userId);


    /**
     * 修改通用机打发票
     *
     * @param invoiceMachine 通用机打发票
     * @return 结果
     */
    public int updateInvoiceMachine(InvoiceMachine invoiceMachine);

    /**
     * 批量删除通用机打发票
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteInvoiceMachineByIds(List<String> ids);

    /**
     * 删除通用机打发票信息
     *
     * @param machineInvoiceId 通用机打发票ID
     * @return 结果
     */
    public int deleteInvoiceMachineById(Long machineInvoiceId);


    /**
     * 通过发票号码查询发票
     */
    public InvoiceMachine selectInvoiceMachineByNumber(InvoiceMachineAddVO invoiceMachineAddVO);

    /**
     * 通过id查询
     * @return
     */
    InvoiceMachine selectById(Long id);
}
