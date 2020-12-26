package com.hbhb.cw.invoice.mapper;

import com.hbhb.cw.invoice.model.InvoiceLibrary;
import com.hbhb.cw.invoice.web.vo.InvoiceInspectionReqVO;
import com.hbhb.cw.invoice.web.vo.InvoiceInspectionVO;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InvoiceLibraryMapper extends BaseMapper<InvoiceLibrary, Long> {
    int insertBatch(@Param("list") List<InvoiceLibrary> list);

    String selectTime();

    List<InvoiceInspectionVO> selectInspectionList(@Param("cond") InvoiceInspectionReqVO cond, @Param("unitId") Integer unitId);

    int countInspectionList(@Param("cond") InvoiceInspectionReqVO cond, @Param("unitId") Integer unitId);

    List<InvoiceInspectionVO> selectInspectionParentList(@Param("cond") InvoiceInspectionReqVO cond, @Param("list") List<Integer> unitIds);

    int countInspectionParentList(@Param("cond") InvoiceInspectionReqVO cond, @Param("list") List<Integer> unitIds);
}