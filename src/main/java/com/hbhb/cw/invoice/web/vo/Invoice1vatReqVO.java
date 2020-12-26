package com.hbhb.cw.invoice.web.vo;

import java.io.Serializable;
import java.math.BigDecimal;
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
public class Invoice1vatReqVO implements Serializable {

    private static final long serialVersionUID = -5809267501458555745L;
    /**
     * 发票代码(必输)
     */
    @Schema(description = "发票代码(必输)")
    private String invoiceCode;

    /**
     * 发票号码
     */
    @Schema(description = "发票号码")
    private String invoiceNumber;
    ;

    /**
     * 开票日期(格式YYYY/MM/DD)
     */
    @Schema(description = "发票日期")
    private String invoiceDate;

    /**
     * 不含税金额(必输)
     */
    @Schema(description = "不含税金额(必输)")
    private BigDecimal taxFreeAmount;

    /**
     * 购方税号
     */
    @Schema(description = "购方税号")
    private String buyerTaxId;

    /**
     * 分公司
     */
    @Schema(description = "分公司")
    private String branch;

    /**
     * 导入时间
     */
    @Schema(description = "导入时间 ")
    private Date iTime;

    /**
     * 导入人员
     */
    @Schema(description = "导入人员 ")
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

    @Schema(description = "userId ")
    private Integer userId;
}
