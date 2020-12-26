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
public class InvoiceRewardDetailed implements Serializable {

    private static final long serialVersionUID = -7356539812060192354L;

    private Long id;

    @ApiModelProperty("地市")
    private String city;

    @ApiModelProperty("县市")
    private Integer unitId;

    @ApiModelProperty("区域营销中心")
    private String marketCenter;

    @ApiModelProperty("渠道类型")
    private String channelType;

    @ApiModelProperty("渠道子类型")
    private String channelSonType;

    @ApiModelProperty("代销商编号")
    private String agentNum;

    @ApiModelProperty("代销商")
    private String agent;

    @ApiModelProperty("科目类型")
    private String projectType;

    @ApiModelProperty("业务类型")
    private String businessType;

    @ApiModelProperty("品牌")
    private String brand;

    @ApiModelProperty("平均单价")
    private BigDecimal averagePrice;

    @ApiModelProperty("计算数量")
    private BigDecimal tally;

    @ApiModelProperty("酬金金额（含税）")
    private BigDecimal ratReward;

    @ApiModelProperty("实付金额")
    private BigDecimal actuallyPaid;

    @ApiModelProperty("计算依据")
    private String calculationBasis;

    @ApiModelProperty("酬金金额（不含税）")
    private BigDecimal reward;

    @ApiModelProperty("账期")
    private String accountPeriod;

    private Date createTime;

    private String createBy;
}