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
 * @since 2020-09-14
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceMonthExportVO implements Serializable {

    private static final long serialVersionUID = -2339540889735532477L;

    @ExcelProperty(value = "分公司", index = 0)
    private String unitName;

    @ExcelProperty(value = "发票类型", index = 1)
    private String invoiceType;

    @ExcelProperty(value = "酬金月份", index = 2)
    private String channelMonth;

    @ExcelProperty(value = "本次实付汇总（元）", index = 3)
    private BigDecimal actualPaymentSum;

    @ExcelProperty(value = "税率", index = 4)
    private BigDecimal taxRate;

    @ExcelProperty(value = "不含税金额总和", index = 5)
    private BigDecimal taxFreeAmountSum;
}
