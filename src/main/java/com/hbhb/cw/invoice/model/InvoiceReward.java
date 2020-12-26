package com.hbhb.cw.invoice.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceReward implements Serializable {

    private static final long serialVersionUID = -4777359502531938163L;

    private Long id;

    private String serialNumber;

    private String channelMonth;

    private String channelNumber;

    private String channelName;

    private String accountTitle;

    private String payName;

    private String bankName;

    private String bankAccount;

    private BigDecimal amount;

    private BigDecimal amountDue;

    private BigDecimal payment;

    private BigDecimal actualPayment;

    private BigDecimal taxPayment;

    private String invoiceNumber;

    private BigDecimal taxRate;

    private Integer unitId;

    private String importDate;

    private Date createTime;

    private String createBy;

    private String updateBy;

    private Date updateTime;
}