package com.hbhb.cw.invoice.mapper;

import com.hbhb.cw.invoice.model.InvoiceRewardDetailed;
import com.hbhb.cw.invoice.web.vo.InvoiceDetailedVO;
import com.hbhb.cw.invoice.web.vo.InvoiceRewardDetailedResVO;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InvoiceRewardDetailedMapper extends BaseMapper<InvoiceRewardDetailed, Long> {
    List<InvoiceRewardDetailedResVO> selectByCond(@Param("cond") InvoiceDetailedVO cond,
                                                  @Param("list") List<Integer> unitIds);

    int countByCond(@Param("cond") InvoiceDetailedVO cond,
                    @Param("list") List<Integer> unitIds);

    int insertBatch(@Param("list") List<InvoiceRewardDetailed> list );

    int deleteByUnitId(@Param("unitId") Integer unitId);
}