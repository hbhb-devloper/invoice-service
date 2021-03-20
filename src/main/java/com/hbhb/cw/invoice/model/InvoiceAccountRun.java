package com.hbhb.cw.invoice.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description ="分公司")
    private Integer unitId;

    @Schema(description ="渠道编号")
    private String channelNum;

    @Schema(description ="渠道名称")
    private String channelName;

    @Schema(description ="业务酬金(上月余额）")
    private BigDecimal lastReward;

    @Schema(description ="综合补贴（上月余额）")
    private BigDecimal lastSubsidy;

    @Schema(description ="不含税金额（业务酬金）（增加）")
    private BigDecimal rewardFreeAmount;

    @Schema(description ="税额（业务酬金）（增加）")
    private BigDecimal rewardTaxAmount;

    @Schema(description ="不含税金额（综合补贴）（增加）")
    private BigDecimal subsidyFreeAmount;

    @Schema(description ="税额（综合补贴）（增加）")
    private BigDecimal subsidyTaxAmount;

    @Schema(description ="不含税金额（业务酬金）（支出）")
    private BigDecimal rewardFreePay;

    @Schema(description ="税额（业务酬金）（支出）")
    private BigDecimal rewardTaxPay;

    @Schema(description ="不含税金额（综合补贴）（支出）")
    private BigDecimal subsidyFreePay;

    @Schema(description ="税额（综合补贴）（支出）")
    private BigDecimal subsidyTaxPay;

    @Schema(description ="业务酬金（本月余额）")
    private BigDecimal thisReward;

    @Schema(description ="综合补贴（本月余额）")
    private BigDecimal thisSubsidy;

    @Schema(description ="小计（本月余额）")
    private BigDecimal subtotal;

    @Schema(description ="渠道负责人")
    private String channelBy;

    private Date createTime;

    private String createBy;
}