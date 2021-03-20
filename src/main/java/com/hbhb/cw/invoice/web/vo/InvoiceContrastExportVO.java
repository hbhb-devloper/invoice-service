package com.hbhb.cw.invoice.web.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yzc
 * @since 2020-12-04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceContrastExportVO  implements Serializable {

    private static final long serialVersionUID = 8196756178795129571L;

    @Schema(description = "往来账余额（业务酬金）")
    private BigDecimal thisReward;

    @Schema(description = "往来账余额（2综合补贴）")
    private BigDecimal thisSubsidy;

    @Schema(description = "往来账余额（3小计）")
    private BigDecimal subtotal;

    @Schema(description = "税率")
    private BigDecimal taxRate;

    @Schema(description = "集中化应付不含税(业务酬金）")
    private BigDecimal reward;

    @Schema(description = "集中化应付不含税(综合补贴）")
    private BigDecimal taxAmount;

    @Schema(description = "集中化应付不含税(小计）")
    private BigDecimal payableSum;

    @Schema(description = "A/E取低的结果")
    private BigDecimal lowReward;

    @Schema(description = "B/F取低的结果")
    private BigDecimal lowSubsidy;

    @Schema(description = "C/G取低的结果 ")
    private BigDecimal lowSubtotal;

    @Schema(description = "渠道编号")
    private String channelNum;

    @Schema(description = "付款名称")
    private String payName;

    @Schema(description = "分公司")
    private String unitId;
}
