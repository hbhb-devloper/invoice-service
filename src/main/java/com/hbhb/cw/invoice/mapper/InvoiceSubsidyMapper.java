package com.hbhb.cw.invoice.mapper;

import com.hbhb.cw.invoice.model.InvoiceSubsidy;
import com.hbhb.cw.invoice.web.vo.InvoiceSubsidyVO;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InvoiceSubsidyMapper extends BaseMapper<InvoiceSubsidy, Long> {
    int insertBatch(@Param("list") List<InvoiceSubsidy> list);

    Long deleteByMonth(@Param("month") String month, @Param("unitId") Integer unitId);

    List<InvoiceSubsidyVO> selectListByMonth(String importDate);
}