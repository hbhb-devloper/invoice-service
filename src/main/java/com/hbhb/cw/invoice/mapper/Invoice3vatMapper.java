package com.hbhb.cw.invoice.mapper;

import com.hbhb.cw.invoice.model.Invoice3vat;
import com.hbhb.cw.invoice.web.vo.Invoice3ResVO;
import com.hbhb.cw.invoice.web.vo.Invoice3vatReqVO;
import com.hbhb.cw.invoice.web.vo.InvoiceByCondVO;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface Invoice3vatMapper extends BaseMapper<Invoice3vat, Long> {

    List<Invoice3ResVO> selectListByCond(Invoice3vatReqVO invoice3vatReqVO);

    int countByCond(Invoice3vatReqVO invoice3vatReqVO);

    List<Invoice3vat> selectListByIds(@Param("list") List<Long> ids);

    Invoice3vat selectByNumber(InvoiceByCondVO invoiceByCondVO);

    int deleteBatchByIds(@Param("list") List<String> idList);

    Invoice3vat selectById(Long vat3Id);
}