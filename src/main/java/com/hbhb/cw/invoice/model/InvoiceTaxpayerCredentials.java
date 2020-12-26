package com.hbhb.cw.invoice.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty("分公司")
    private Integer unitId;

    @ApiModelProperty("渠道编号")
    private String channelNum;

    @ApiModelProperty("渠道名称")
    private String channelName;

    @ApiModelProperty("纳税人资质")
    private Integer taxpayerCredentials;

    @ApiModelProperty("税率")
    private BigDecimal taxRate;

    @ApiModelProperty("更新时间")
    private Date updateTime;

    @ApiModelProperty("销售方名称")
    private String sellerName;

    @ApiModelProperty("销售方纳税人识别号")
    private String sellerTaxpayerCode;

    @ApiModelProperty("营业执照名称")
    private String businessLicense;

    @ApiModelProperty("营业执照纳税人识别号")
    private String businessTaxpayerCode;

    @ApiModelProperty("法人")
    private String legalPerson;

    private Date createTime;

    private String createBy;
}