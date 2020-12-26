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
 * @since 2020-09-16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceCheckExportVO implements Serializable {

    private static final long serialVersionUID = 9175717214036964299L;

    @ExcelProperty(value = "分公司名称", index = 0)
    private String unitName;

    @ExcelProperty(value = "渠道编号", index = 1)
    private String channelNumber;

    @ExcelProperty(value = "正常酬金报账金额（不含税）", index = 2)
    private BigDecimal amountSum;

    @ExcelProperty(value = "往来账业务酬金", index = 3)
    private BigDecimal accountAmount;

    @ExcelProperty(value = "正常酬金报账金额验证", index = 4)
    private String amountCheck;

    @ExcelProperty(value = "综合补贴报账金额（不含税）", index = 5)
    private BigDecimal subsidySum;

    @ExcelProperty(value = "往来账综合补贴", index = 6)
    private BigDecimal accountSubsidy;

    @ExcelProperty(value = "综合补贴报账金额验证", index = 7)
    private String subsidyCheck;

    @ExcelProperty(value = "求和项:小计（不含税）", index = 8)
    private BigDecimal sum;

    @ExcelProperty(value = "往来账小计", index = 9)
    private BigDecimal accountSum;

    @ExcelProperty(value = "往来账小计验证", index = 10)
    private String sumCheck;

    @ExcelProperty(value = "账期", index = 11)
    private String date;
}
