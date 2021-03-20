package com.hbhb.cw.invoice.mapper;

import com.hbhb.cw.invoice.model.Invoice6vat;
import com.hbhb.cw.invoice.web.vo.Invoice6ResVO;
import com.hbhb.cw.invoice.web.vo.Invoice6vatReqVO;
import com.hbhb.cw.invoice.web.vo.InvoiceByCondVO;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface Invoice6vatMapper extends BaseMapper<Invoice6vat, Long> {

    List<Invoice6ResVO> selectListByCond(Invoice6vatReqVO invoice6vatReqVO);

    int countByCond(Invoice6vatReqVO invoice6vatReqVO);

    List<Invoice6vat> selectListByIds(@Param("list") List<Long> ids);

    Invoice6vat selectByNumber(InvoiceByCondVO invoiceByCondVO);

    int deleteBatchByIds(@Param("list") List<String> idList);

    Invoice6vat selectById(Long vat6Id);
}