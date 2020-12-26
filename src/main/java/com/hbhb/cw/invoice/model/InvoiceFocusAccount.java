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
public class InvoiceFocusAccount implements Serializable {

    private static final long serialVersionUID = 1601151108453764345L;
    private Long id;

    @ApiModelProperty("酬金月份")
    private String rewardMonth;

    @ApiModelProperty("地市")
    private String city;

    @ApiModelProperty("县市")
    private Integer unitId;

    @ApiModelProperty("片区")
    private String area;

    @ApiModelProperty("渠道编号")
    private String channelNum;

    @ApiModelProperty("渠道名称")
    private String channelName;

    @ApiModelProperty("一级类别")
    private String firstLevel;

    @ApiModelProperty("二级类别")
    private String secondLevel;

    @ApiModelProperty("三级类别")
    private String thirdLevel;

    @ApiModelProperty("负责人")
    private String director;

    @ApiModelProperty("代扣代缴")
    private Long withhold;

    @ApiModelProperty("付款名称")
    private String payName;

    @ApiModelProperty("开户行")
    private String bank;

    @ApiModelProperty("开户支行")
    private String bankBranch;

    @ApiModelProperty("银行账号")
    private String bankAccount;

    @ApiModelProperty("酬金（元）")
    private BigDecimal reward;

    @ApiModelProperty("应付（元）")
    private BigDecimal payable;

    @ApiModelProperty("支付状态")
    private String payState;

    @ApiModelProperty("开票信息")
    private String invoiceOpen;

    @ApiModelProperty("酬金发票编号")
    private String rewardNumber;

    @ApiModelProperty("用途")
    private String purpose;

    private Date createTime;

    private String createBy;
}