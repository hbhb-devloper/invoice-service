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
public class InvoiceRewardDetailedResVO implements Serializable {

    private static final long serialVersionUID = -8910966444118823079L;

    @Schema(description = "地市")
    private String city;

    @Schema(description = "县市")
    private String unitId;

    @Schema(description = "区域营销中心")
    private String marketCenter;

    @Schema(description = "渠道类型")
    private String channelType;

    @Schema(description = "渠道子类型")
    private String channelSonType;

    @Schema(description = "代销商编号")
    private String agentNum;

    @Schema(description = "代销商")
    private String agent;

    @Schema(description = "科目类型")
    private String projectType;

    @Schema(description = "业务类型")
    private String businessType;

    @Schema(description = "品牌")
    private String brand;

    @Schema(description = "平均单价")
    private BigDecimal averagePrice;

    @Schema(description = "计算数量")
    private BigDecimal tally;

    @Schema(description = "酬金金额（含税）")
    private BigDecimal ratReward;

    @Schema(description = "实付金额")
    private BigDecimal actuallyPaid;

    @Schema(description = "计算依据")
    private String calculationBasis;

    @Schema(description = "酬金金额（不含税）")
    private BigDecimal reward;

    @Schema(description = "账期")
    private String accountPeriod;
}
