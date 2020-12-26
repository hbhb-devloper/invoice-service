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
 * @since 2020-10-24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class InvoiceIncentiveResVO implements Serializable {

    private static final long serialVersionUID = -2285435792454227006L;

    @Schema(description = "分公司")
    private String unitId;

    @Schema(description = "渠道编号")
    private String channelNum;

    @Schema(description = "渠道名称")
    private String channelName;

    @Schema(description = "综合激励含税金额")
    private BigDecimal taxAmount;
}
