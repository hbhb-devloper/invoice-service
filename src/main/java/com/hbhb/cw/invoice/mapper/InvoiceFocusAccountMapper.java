package com.hbhb.cw.invoice.mapper;

import com.hbhb.cw.invoice.model.InvoiceFocusAccount;
import com.hbhb.cw.invoice.web.vo.InvoiceFocusAccountResVO;
import com.hbhb.cw.invoice.web.vo.InvoiceFocusVO;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InvoiceFocusAccountMapper extends BaseMapper<InvoiceFocusAccount, Long> {

    List<InvoiceFocusAccountResVO> selectByCond(@Param("cond") InvoiceFocusVO cond,
                                                @Param("list") List<Integer> list);

    int countByCond(@Param("cond") InvoiceFocusVO cond,
                    @Param("list") List<Integer> list);

    List<InvoiceFocusAccountResVO> selectByParentCond(@Param("list") List<Integer> list, @Param("cond") InvoiceFocusVO cond);

    int countByParentCond(@Param("list") List<Integer> list, @Param("cond") InvoiceFocusVO cond);

    int insertBatch(@Param("list") List<InvoiceFocusAccount> list );

    int deleteByUnitId(@Param("unitId") Integer unitId);
}