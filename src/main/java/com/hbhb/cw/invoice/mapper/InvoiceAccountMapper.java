package com.hbhb.cw.invoice.mapper;

import com.hbhb.cw.invoice.model.InvoiceAccount;
import com.hbhb.cw.invoice.web.vo.InvoiceAccountVO;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InvoiceAccountMapper extends BaseMapper<InvoiceAccount, Long> {
    int insertBatch(@Param("list") List<InvoiceAccount> list);

    int deleteByUnitId(@Param("unitId") Integer unitId);

    List<InvoiceAccountVO> selectList();
}