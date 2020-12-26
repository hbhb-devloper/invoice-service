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
public class InvoiceAccountRun implements Serializable {

    private static final long serialVersionUID = -5598410733113747766L;
    private Long id;

    @ApiModelProperty("分公司")
    private Integer unitId;

    @ApiModelProperty("渠道编号")
    private String channelNum;

    @ApiModelProperty("渠道名称")
    private String channelName;

    @ApiModelProperty("业务酬金(上月余额）")
    private BigDecimal lastReward;

    @ApiModelProperty("综合补贴（上月余额）")
    private BigDecimal lastSubsidy;

    @ApiModelProperty("不含税金额（业务酬金）（增加）")
    private BigDecimal rewardFreeAmount;

    @ApiModelProperty("税额（业务酬金）（增加）")
    private BigDecimal rewardTaxAmount;

    @ApiModelProperty("不含税金额（综合补贴）（增加）")
    private BigDecimal subsidyFreeAmount;

    @ApiModelProperty("税额（综合补贴）（增加）")
    private BigDecimal subsidyTaxAmount;

    @ApiModelProperty("不含税金额（业务酬金）（支出）")
    private BigDecimal rewardFreePay;

    @ApiModelProperty("税额（业务酬金）（支出）")
    private BigDecimal rewardTaxPay;

    @ApiModelProperty("不含税金额（综合补贴）（支出）")
    private BigDecimal subsidyFreePay;

    @ApiModelProperty("税额（综合补贴）（支出）")
    private BigDecimal subsidyTaxPay;

    @ApiModelProperty("业务酬金（本月余额）")
    private BigDecimal thisReward;

    @ApiModelProperty("综合补贴（本月余额）")
    private BigDecimal thisSubsidy;

    @ApiModelProperty("小计（本月余额）")
    private BigDecimal subtotal;

    @ApiModelProperty("渠道负责人")
    private String channelBy;

    private Date createTime;

    private String createBy;
}