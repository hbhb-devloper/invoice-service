package com.hbhb.cw.invoice.web.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yzc
 * @since 2020-09-11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceRewardResVO implements Serializable {
    private static final long serialVersionUID = 3592094779335611929L;

    @Schema(description = "报账流水")
    private String serialNumber;

    @Schema(description = "渠道月份")
    private String channelMonth;

    @Schema(description = "渠道编号")
    private String channelNumber;

    @Schema(description = "渠道名称")
    private String channelName;

    @Schema(description = "营业执照对公账户名称")
    private String accountTitle;

    @Schema(description = "付款名称")
    private String payName;

    @Schema(description = "开户支行")
    private String bankName;

    @Schema(description = "银行账号")
    private String bankAccount;

    @Schema(description = "酬金金额")
    private String amount;

    @Schema(description = "应付金额（元）")
    private String amountDue;

    @Schema(description = "本次支付（元）")
    private String payment;

    @Schema(description = "本次实付（元）")
    private String actualPayment;

    @Schema(description = "代缴税费（元）")
    private String taxPayment;

    @Schema(description = "发票编号")
    private String invoiceNumber;

    @Schema(description = "发票税率")
    private BigDecimal taxRate;

    @Schema(description = "分公司")
    private String unitName;
}
