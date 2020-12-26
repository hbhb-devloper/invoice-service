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
public class InvoiceSubsidy implements Serializable {

    private static final long serialVersionUID = -4777359502531938163L;
    private Long id;

    private Integer unitId;

    private String channelNumber;

    private BigDecimal taxSubsidy;

    private String importDate;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;
}