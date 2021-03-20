package com.hbhb.cw.invoice.mapper;

import com.hbhb.cw.invoice.model.InvoiceReward;
import com.hbhb.cw.invoice.web.vo.InvoiceCheckVO;
import com.hbhb.cw.invoice.web.vo.InvoiceMonthExportVO;
import com.hbhb.cw.invoice.web.vo.InvoiceRewardReqVO;
import com.hbhb.cw.invoice.web.vo.InvoiceRewardResVO;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InvoiceRewardMapper extends BaseMapper<InvoiceReward, Long> {
    int insertBatch(@Param("list") List<InvoiceReward> list);

    int countBySerialNumber(String serialNumber);

    List<InvoiceRewardResVO> selectByCond(@Param("cond") InvoiceRewardReqVO cond,@Param("list") List<Integer> list);

    int countByCond(@Param("cond") InvoiceRewardReqVO cond,@Param("list") List<Integer> list);

    List<InvoiceRewardResVO> selectByParentCond(@Param("list") List<Integer> list, @Param("cond") InvoiceRewardReqVO cond);

    int countByParentCond(@Param("list") List<Integer> list, @Param("cond") InvoiceRewardReqVO cond);

    void deleteBySerialNumber(@Param("list") List<String> list);

    List<InvoiceMonthExportVO> censusByCond(@Param("cond") InvoiceRewardReqVO cond, @Param("list") List<Integer> list);

    List<InvoiceMonthExportVO> censusByParentCond(@Param("list") List<Integer> list, @Param("cond") InvoiceRewardReqVO cond);

    List<InvoiceCheckVO> checkListByCond(@Param("cond") InvoiceRewardReqVO cond, @Param("list") List<Integer> list);

    List<InvoiceCheckVO> checkListByParentCond(@Param("list") List<Integer> list, @Param("cond") InvoiceRewardReqVO cond);
}