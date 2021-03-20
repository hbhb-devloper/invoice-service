package com.hbhb.cw.invoice.mapper;

import com.hbhb.cw.invoice.model.InvoiceTaxpayerCredentials;
import com.hbhb.cw.invoice.web.vo.InvoiceTaxpayerResVO;
import com.hbhb.cw.invoice.web.vo.InvoiceTaxpayerVO;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InvoiceTaxpayerCredentialsMapper extends BaseMapper<InvoiceTaxpayerCredentials, Long> {

    List<InvoiceTaxpayerResVO> selectByCond(@Param("cond") InvoiceTaxpayerVO cond);

    int countByCond(@Param("cond") InvoiceTaxpayerVO cond);

    int insertBatch(@Param("list") List<InvoiceTaxpayerCredentials> list);

    List<InvoiceTaxpayerCredentials> selectByChannel(@Param("unitId") Integer unitId, @Param("channelNum") String channelNum);

    int deleteByUnitId(@Param("unitId") Integer unitId);
}