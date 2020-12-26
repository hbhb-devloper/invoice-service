package com.hbhb.cw.invoice.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceLibrary implements Serializable {

    private static final long serialVersionUID = -6204682334845413971L;

    private Long id;

    private String invoiceCode;

    private String invoiceNumber;

    private Date invoiceDate;

    private boolean invoiceState;

    private String sellerTax;

    private String sellerName;

    private String buyerTax;

    private String buyerName;

    private BigDecimal amount;

    private BigDecimal taxAmount;

    private BigDecimal taxIncludeAmount;

    private String checkCode;

    private String sellerContact;

    private String sellerAccount;

    private String buyerContact;

    private String buyerAccount;

    private String pwdArea;

    private String remark;

    private String drawer;

    private String payee;

    private String reviewer;

    private Integer createBy;

    private Date createTime;
}