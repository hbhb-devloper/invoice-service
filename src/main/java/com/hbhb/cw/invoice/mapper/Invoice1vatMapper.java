package com.hbhb.cw.invoice.mapper;

import com.hbhb.cw.invoice.model.Invoice1vat;
import com.hbhb.cw.invoice.web.vo.Invoice1ResVO;
import com.hbhb.cw.invoice.web.vo.Invoice1vatReqVO;
import com.hbhb.cw.invoice.web.vo.InvoiceByCondVO;
import com.hbhb.cw.invoice.web.vo.InvoiceInspectResVat;
import com.hbhb.cw.invoice.web.vo.InvoiceInspectionReqVO;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface Invoice1vatMapper extends BaseMapper<Invoice1vat, Long> {

    List<Invoice1ResVO> selectListByCond(Invoice1vatReqVO invoice1vatReqVO);

    int countByCond(Invoice1vatReqVO invoice1vatReqVO);

    List<Invoice1vat> selectListByIds(@Param("list") List<Long> ids);

    Invoice1vat selectByNumber(InvoiceByCondVO invoiceByCondVO);

    int deleteBatchByIds(@Param("list") List<String> idList);

    Invoice1vat selectById(Long vat1Id);

    List<InvoiceInspectResVat> selectVatByCond(InvoiceInspectionReqVO cond);
}