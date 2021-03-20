package com.hbhb.cw.invoice.web.vo;

import com.alibaba.excel.annotation.ExcelProperty;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yzc
 * @since 2020-09-08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceRewardImportVO implements Serializable {
    private static final long serialVersionUID = -6543895444604777776L;

    @ExcelProperty(value = "报账流水", index = 0)
    private String serialNumber;

    @ExcelProperty(value = "酬金月份", index = 1)
    private String channelMonth;

    @ExcelProperty(value = "渠道编号",index = 2)
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
}
