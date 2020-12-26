package com.hbhb.cw.invoice.mapper;

import com.hbhb.cw.invoice.model.InvoiceGoods;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InvoiceGoodsMapper extends BaseMapper<InvoiceGoods, Long> {
    int insertBatch(@Param("list") List<InvoiceGoods> list);
}