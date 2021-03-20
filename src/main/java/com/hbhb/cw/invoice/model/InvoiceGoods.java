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
public class InvoiceGoods implements Serializable {

    private static final long serialVersionUID = -5231745051674979401L;
    private Long id;

    private String invoiceCode;

    private String invoiceNumber;

    private String taxCode;

    private String goodsName;

    private String model;

    private String unit;

    private String quantity;

    private String price;

    private BigDecimal amount;

    private BigDecimal taxRate;

    private BigDecimal taxAmount;

    private Integer createBy;

    private Date createTime;
}