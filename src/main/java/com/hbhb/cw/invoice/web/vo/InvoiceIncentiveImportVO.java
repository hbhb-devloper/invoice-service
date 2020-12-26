package com.hbhb.cw.invoice.web.vo;

import com.alibaba.excel.annotation.ExcelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yzc
 * @since 2020-10-19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceIncentiveImportVO implements Serializable {

    private static final long serialVersionUID = -2285435792454227006L;

    @ExcelProperty(value = "分公司", index = 0)
    private String unitId;

    @ExcelProperty(value = "渠道编号", index = 1)
    private String channelNum;

    @ExcelProperty(value = "渠道名称", index = 2)
    private String channelName;

    @ExcelProperty(value = "综合激励含税金额", index = 3)
    private BigDecimal taxAmount;

}
