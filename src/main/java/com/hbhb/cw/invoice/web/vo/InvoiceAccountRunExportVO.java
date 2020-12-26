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
 * @since 2020-10-21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceAccountRunExportVO implements Serializable {

    private static final long serialVersionUID = -8029142269335053730L;

    @ExcelProperty(value = "分公司", index = 0)
    private String unitId;

    @ExcelProperty(value = "渠道编号", index = 1)
    private String channelNum;

    @ExcelProperty(value = "渠道名称", index = 2)
    private String channelName;

    @ExcelProperty(value = "业务酬金（本月余额）", index = 3)
    private BigDecimal thisReward;

    @ExcelProperty(value = "综合补贴（本月余额）", index = 4)
    private BigDecimal thisSubsidy;

    @ExcelProperty(value = "小计（本月余额）", index = 5)
    private BigDecimal subtotal;
}
