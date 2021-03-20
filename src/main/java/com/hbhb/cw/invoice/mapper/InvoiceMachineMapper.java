package com.hbhb.cw.invoice.mapper;

import com.hbhb.cw.invoice.model.InvoiceMachine;
import com.hbhb.cw.invoice.web.vo.InvoiceByCondVO;
import com.hbhb.cw.invoice.web.vo.InvoiceMachineReqVO;
import com.hbhb.cw.invoice.web.vo.InvoiceMachineResVO;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InvoiceMachineMapper extends BaseMapper<InvoiceMachine, Long> {

    List<InvoiceMachineResVO> selectListByCond(InvoiceMachineReqVO invoiceMachineReqVO);

    int countByCond(InvoiceMachineReqVO invoiceMachineReqVO);

    List<InvoiceMachine> selectListByIds(@Param("list") List<Long> ids);

    InvoiceMachine selectByNumber(InvoiceByCondVO invoiceByCondVO);

    int deleteBatchByIds(@Param("list") List<String> idList);

    InvoiceMachine selectById(Long invoiceMachineId);
}