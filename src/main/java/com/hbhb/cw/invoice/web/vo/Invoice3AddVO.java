package com.hbhb.cw.invoice.web.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Invoice3AddVO implements Serializable {

    private static final long serialVersionUID = -451557962446628084L;


    /**
     * 发票种类代码（01-增值税专用发票、03-机动车销售统一发票）(必输)
     */
    @Schema(description = "发票种类代码")
    private String invoiceType;

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

    /**
     * 发票页码
     */
    @Schema(description = "发票页码")
    private Integer invoicePageNumber;

    /**
     * 开票日期(格式YYYY/MM/DD)
     */
    @Schema(description = "开票日期")
    private String invoiceDate;

    /**
     * 不含税金额(必输)
     */
    @Schema(description = "不含税金额(必输))")
    private BigDecimal taxFreeAmount;

    /**
     * 购方税号
     */
    @Schema(description = "购方税号")
    private String buyerTaxId;

    /**
     * 项目属性(0-不动产;1-动产;2-其它)
     */
    @Schema(description = "项目属性(0-不动产;1-动产;2-其它)")
    private String projectProperties;

    /**
     * 红字信息表号
     */
    @Schema(description = "红字信息表号")
    private String tableNumber;
}
