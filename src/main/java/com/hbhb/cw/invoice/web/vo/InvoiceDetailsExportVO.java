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
 * @since 2020-09-12
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDetailsExportVO implements Serializable {

    private static final long serialVersionUID = -425682834275178977L;

    @ExcelProperty(value = "报账流水", index = 0)
    private String serialNumber;

    @ExcelProperty(value = "酬金月份", index = 1)
    private String channelMonth;

    @ExcelProperty(value = "渠道编号", index = 2)
    private String channelNumber;

    @ExcelProperty(value = "渠道名称", index = 3)
    private String channelName;

    @ExcelProperty(value = "营业执照对公账户名称", index = 4)
    private String accountTitle;

    @ExcelProperty(value = "付款名称", index = 5)
    private String payName;

    @ExcelProperty(value = "开户支行", index = 6)
    private String bankName;

    @ExcelProperty(value = "银行账号", index = 7)
    private String bankAccount;

    @ExcelProperty(value = "酬金金额", index = 8)
    private String amount;

    @ExcelProperty(value = "应付金额（元）", index = 9)
    private String amountDue;

    @ExcelProperty(value = "本次支付（元）", index = 10)
    private String payment;

    @ExcelProperty(value = "本次实付（元）", index = 11)
    private String actualPayment;

    @ExcelProperty(value = "代缴税费（元）", index = 12)
    private String taxPayment;

    @ExcelProperty(value = "发票编号", index = 13)
    private String invoiceNumber;

    @ExcelProperty(value = "验证", index = 14)
    private BigDecimal verification;

    @ExcelProperty(value = "分公司", index = 15)
    private String unitName;

    @ExcelProperty(value = "发票类型", index = 16)
    private String invoiceType;

    @ExcelProperty(value = "税率", index = 17)
    private BigDecimal taxRate;

    @ExcelProperty(value = "不含税金额", index = 18)
    private BigDecimal taxFreeAmount;
}
