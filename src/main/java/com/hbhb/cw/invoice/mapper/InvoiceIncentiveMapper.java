package com.hbhb.cw.invoice.mapper;

import com.hbhb.cw.invoice.model.InvoiceIncentive;
import com.hbhb.cw.invoice.web.vo.InvoiceIncentiveResVO;
import com.hbhb.cw.invoice.web.vo.InvoiceIncentiveVO;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InvoiceIncentiveMapper extends BaseMapper<InvoiceIncentive, Long> {
    
    List<InvoiceIncentiveResVO> selectByCond(@Param("cond") InvoiceIncentiveVO cond,@Param("list") List<Integer> list);

    int countByCond(@Param("cond") InvoiceIncentiveVO cond,@Param("list") List<Integer> list);

    int insertBatch(@Param("list") List<InvoiceIncentive> list);

    int deleteByUnitId(@Param("unitId") Integer unitId);
}
