package com.hbhb.cw.invoice.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceIncentive implements Serializable {

    private static final long serialVersionUID = -2285435792454227006L;
    private Long id;

    @Schema(description ="分公司")
    private Integer unitId;

    @Schema(description ="渠道编号")
    private String channelNum;

    @Schema(description ="渠道名称")
    private String channelName;

    @Schema(description ="综合激励含税金额")
    private BigDecimal taxAmount;

    private Date createTime;

    private String createBy;
}