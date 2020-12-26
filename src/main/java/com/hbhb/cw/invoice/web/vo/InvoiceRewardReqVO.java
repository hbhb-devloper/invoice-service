package com.hbhb.cw.invoice.web.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yzc
 * @since 2020-09-10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceRewardReqVO implements Serializable {
    private static final long serialVersionUID = 7679404037765512050L;

    @Schema(description = "报账流水")
    private String serialNumber;

    @Schema(description = "渠道月份（仅第二个页面）")
    private String channelMonth;

    @Schema(description = "渠道名称（仅第二个页面）")
    private String channelName;

    @Schema(description = "渠道编号（仅第二个页面）")
    private String channelNumber;

    @Schema(description = "导入时间（账期）")
    private String importDate;

    @Schema(description = "税率")
    private BigDecimal taxRate;

    @Schema(description = "单位id（仅第二个页面）")
    private Integer unitId;
}
