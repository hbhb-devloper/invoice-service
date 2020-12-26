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
public class InvoiceAccountRunImportVO implements Serializable {

    private static final long serialVersionUID = -4757544592920921981L;

    @ExcelProperty(value = "分公司", index = 0)
    private String unitId;

    @ExcelProperty(value = "渠道编号", index = 1)
    private String channelNum;

    @ExcelProperty(value = "渠道名称", index = 2)
    private String channelName;

    @ExcelProperty(value = "业务酬金(上月余额）", index = 3)
    private BigDecimal lastReward;

    @ExcelProperty(value = "综合补贴（上月余额）", index = 4)
    private BigDecimal lastSubsidy;

    @ExcelProperty(value = "不含税金额（业务酬金）（增加）", index = 5)
    private BigDecimal rewardFreeAmount;

    @ExcelProperty(value = "税额（业务酬金）（增加）", index = 6)
    private BigDecimal rewardTaxAmount;

    @ExcelProperty(value = "不含税金额（综合补贴）（增加）", index = 7)
    private BigDecimal subsidyFreeAmount;

    @ExcelProperty(value = "税额（综合补贴）（增加）", index = 8)
    private BigDecimal subsidyTaxAmount;

    @ExcelProperty(value = "不含税金额（业务酬金）（支出）", index = 9)
    private BigDecimal rewardFreePay;

    @ExcelProperty(value = "税额（业务酬金）（支出）", index = 10)
    private BigDecimal rewardTaxPay;

    @ExcelProperty(value = "不含税金额（综合补贴）（支出）", index = 11)
    private BigDecimal subsidyFreePay;

    @ExcelProperty(value = "税额（综合补贴）（支出）", index = 12)
    private BigDecimal subsidyTaxPay;

    @ExcelProperty(value = "业务酬金（本月余额）", index = 13)
    private BigDecimal thisReward;

    @ExcelProperty(value = "综合补贴（本月余额）", index = 14)
    private BigDecimal thisSubsidy;

    @ExcelProperty(value = "小计（本月余额）", index = 15)
    private BigDecimal subtotal;

    @ExcelProperty(value = "渠道负责人", index = 16)
    private String channelBy;
}

