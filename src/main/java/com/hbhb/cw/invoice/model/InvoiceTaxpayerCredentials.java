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
public class InvoiceTaxpayerCredentials implements Serializable {

    private static final long serialVersionUID = 4388980478150054300L;
    private Long id;

    @Schema(description ="分公司")
    private Integer unitId;

    @Schema(description ="渠道编号")
    private String channelNum;

    @Schema(description ="渠道名称")
    private String channelName;

    @Schema(description ="纳税人资质")
    private Integer taxpayerCredentials;

    @Schema(description ="税率")
    private BigDecimal taxRate;

    @Schema(description ="更新时间")
    private Date updateTime;

    @Schema(description ="销售方名称")
    private String sellerName;

    @Schema(description ="销售方纳税人识别号")
    private String sellerTaxpayerCode;

    @Schema(description ="营业执照名称")
    private String businessLicense;

    @Schema(description ="营业执照纳税人识别号")
    private String businessTaxpayerCode;

    @Schema(description ="法人")
    private String legalPerson;

    private Date createTime;

    private String createBy;
}