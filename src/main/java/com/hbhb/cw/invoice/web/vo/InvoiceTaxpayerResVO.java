package com.hbhb.cw.invoice.web.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yzc
 * @since 2020-10-24
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceTaxpayerResVO implements Serializable {

    private static final long serialVersionUID = -3787766825010569034L;

    @Schema(description = "分公司")
    private String unitId;

    @Schema(description = "渠道编号")
    private String channelNum;

    @Schema(description = "渠道名称")
    private String channelName;

    @Schema(description = "纳税人资质")
    private Integer taxpayerCredentials;

    @Schema(description = "税率")
    private BigDecimal taxRate;

    @Schema(description = "更新时间")
    private Date updateTime;

    @Schema(description = "销售方名称")
    private String sellerName;

    @Schema(description = "销售方纳税人识别号")
    private String sellerTaxpayerCode;

    @Schema(description = "营业执照名称")
    private String businessLicense;

    @Schema(description = "营业执照纳税人识别号")
    private String businessTaxpayerCode;

    @Schema(description = "法人")
    private String legalPerson;
}
