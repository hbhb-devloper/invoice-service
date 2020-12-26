package com.hbhb.cw.invoice.mapper;

import com.hbhb.cw.invoice.model.InvoiceVat;
import com.hbhb.cw.invoice.web.vo.InvoiceByCondVO;
import com.hbhb.cw.invoice.web.vo.InvoiceVatReqVO;
import com.hbhb.cw.invoice.web.vo.InvoiceVatResVO;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InvoiceVatMapper extends BaseMapper<InvoiceVat, Long> {

    List<InvoiceVatResVO> selectListByCond(InvoiceVatReqVO invoiceVatReqVO);

    int countByCond(InvoiceVatReqVO invoiceVatReqVO);

    List<InvoiceVat> selectListByIds(@Param("list") List<Long> ids);

    InvoiceVat selectByNumber(InvoiceByCondVO invoiceByCondVO);

    int deleteBatchByIds(@Param("list") List<String> idList);

    InvoiceVat selectById(Long vatId);
}