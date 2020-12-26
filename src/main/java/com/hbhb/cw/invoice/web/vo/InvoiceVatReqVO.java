package com.hbhb.cw.invoice.web.vo;

import java.io.Serializable;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceVatReqVO implements Serializable {

    private static final long serialVersionUID = 1552367862775450324L;
    /**
     * 发票代码
     */
    @Schema(description = "发票代码")
    private String invoiceCode;

    /**
     * 发票号码
     */
    @Schema(description = "发票号码")
    private String invoiceNumber;

    /**
     * 开票日期(格式YYYY/MM/DD)
     */
    @Schema(description = "开票日期")
    private String invoiceDate;

    /**
     * 校验码后六位
     */
    @Schema(description = "校验码后六位")
    private String checkCode;

    /**
     * 购方税号
     */
    @Schema(description = "购方税号")
    private String buyerTaxId;

    /**
     * 不含税金额
     */
    @Schema(description = "不含税金额")
    private String taxFreeAmount;

    /**
     * 分公司
     */
    @Schema(description = "分公司 ")
    private String branch;

    /**
     * 导入时间
     */
    @Schema(description = "导入时间")
    private Date iTime;

    /**
     * 导入人员
     */
    private String personnel;

    /**
     * 开始时间
     */
    @Schema(description = "开始时间")
    private String beginTime;

    /**
     * 结束时间
     */
    @Schema(description = "结束时间")
    private String endTime;

    private Integer userId;

}
