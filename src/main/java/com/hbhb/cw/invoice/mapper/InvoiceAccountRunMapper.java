package com.hbhb.cw.invoice.mapper;

import com.hbhb.cw.invoice.model.InvoiceAccountRun;
import com.hbhb.cw.invoice.web.vo.InvoiceAccountRunResVO;
import com.hbhb.cw.invoice.web.vo.InvoiceAccountRunVO;
import com.hbhb.cw.invoice.web.vo.InvoiceContrastReqVO;
import com.hbhb.cw.invoice.web.vo.InvoiceContrastVO;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InvoiceAccountRunMapper extends BaseMapper<InvoiceAccountRun, Long> {

    List<InvoiceAccountRunResVO> selectByCond(@Param("cond") InvoiceAccountRunVO cond, List<Integer> list);

    int countByCond(@Param("cond") InvoiceAccountRunVO cond, List<Integer> list);

    String selectTime(int unitId);

    int insertBatch(@Param("list") List<InvoiceAccountRun> list);

    int deleteByUnitId(@Param("unitId") int unitId);

    List<InvoiceContrastVO> selectContrastByCond(@Param("cond") InvoiceContrastReqVO cond,
                                                 @Param("list") List<Integer> unitIds);

    int selectContrastCountByCond(@Param("cond") InvoiceContrastReqVO cond,
                                  @Param("list") List<Integer> unitIds);
}