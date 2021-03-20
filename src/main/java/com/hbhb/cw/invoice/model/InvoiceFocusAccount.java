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
public class InvoiceFocusAccount implements Serializable {

    private static final long serialVersionUID = 1601151108453764345L;
    private Long id;

    @Schema(description ="酬金月份")
    private String rewardMonth;

    @Schema(description ="地市")
    private String city;

    @Schema(description ="县市")
    private Integer unitId;

    @Schema(description ="片区")
    private String area;

    @Schema(description ="渠道编号")
    private String channelNum;

    @Schema(description ="渠道名称")
    private String channelName;

    @Schema(description ="一级类别")
    private String firstLevel;

    @Schema(description ="二级类别")
    private String secondLevel;

    @Schema(description ="三级类别")
    private String thirdLevel;

    @Schema(description ="负责人")
    private String director;

    @Schema(description ="代扣代缴")
    private Long withhold;

    @Schema(description ="付款名称")
    private String payName;

    @Schema(description ="开户行")
    private String bank;

    @Schema(description ="开户支行")
    private String bankBranch;

    @Schema(description ="银行账号")
    private String bankAccount;

    @Schema(description ="酬金（元）")
    private BigDecimal reward;

    @Schema(description ="应付（元）")
    private BigDecimal payable;

    @Schema(description ="支付状态")
    private String payState;

    @Schema(description ="开票信息")
    private String invoiceOpen;

    @Schema(description ="酬金发票编号")
    private String rewardNumber;

    @Schema(description ="用途")
    private String purpose;

    private Date createTime;

    private String createBy;
}