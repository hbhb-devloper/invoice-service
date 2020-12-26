package com.hbhb.cw.invoice.web.vo;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yzc
 * @since 2020-10-20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceTaxpayerVO implements Serializable {

    private static final long serialVersionUID = -2615612666828501224L;

    @Schema(description = "渠道编号")
    private String channelNum;

    @Schema(description = "发票销售方名称")
    private String sellerName;

    @Schema(description = "发票销售纳税人识别号")
    private String sellerTaxpayerCode;

    @Schema(description = "营业执照名称")
    private String businessLicense;

    @Schema(description = "营业执照纳税人识别号")
    private String businessTaxpayerCode;

    @Schema(description = "纳税人资质")
    private Integer taxpayerCredentials;
}
