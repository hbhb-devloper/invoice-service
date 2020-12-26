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
public class InvoiceAccount implements Serializable {

    private static final long serialVersionUID = 8327700849491976829L;

    private Long id;

    private Integer unitId;

    private String channelNumber;

    private String channelName;

    private BigDecimal amount;

    private BigDecimal subsidy;

    private BigDecimal subtotal;

    private String currentAccount;

    private Date createTime;

    private String createBy;

    private String updateBy;

    private Date updateTime;
}