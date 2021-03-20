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
 * @since 2020-09-14
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceCheckVO implements Serializable {

    private static final long serialVersionUID = 8510643910426588032L;

    @Schema(description = "分公司名称")
    private String unitName;

    @Schema(description = "税率")
    private BigDecimal taxRate;

    @Schema(description = "渠道编号")
    private String channelNumber;

    @Schema(description = "正常酬金报账金额（不含税）")
    private BigDecimal amountSum;

    @Schema(description = "往来账业务酬金")
    private BigDecimal accountAmount;

    @Schema(description = "正常酬金报账金额验证")
    private String amountCheck;

    @Schema(description = "综合补贴报账金额（不含税）")
    private BigDecimal subsidySum;

    @Schema(description = "往来账综合补贴")
    private BigDecimal accountSubsidy;

    @Schema(description = "综合补贴报账金额验证")
    private String subsidyCheck;

    @Schema(description = "求和项:小计（不含税）")
    private BigDecimal sum;

    @Schema(description = "往来账小计")
    private BigDecimal accountSum;

    @Schema(description = "往来账小计验证")
    private String sumCheck;

    @Schema(description = "账期")
    private String date;
}
