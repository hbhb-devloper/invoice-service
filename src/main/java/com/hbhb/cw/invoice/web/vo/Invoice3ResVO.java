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
public class Invoice3ResVO implements Serializable {

    private static final long serialVersionUID = 5468912221205493025L;
    @Schema(description ="id")
    private Long vat3Id;

    @Schema(description ="行号(必输)")
    private String lineNumber;

    @Schema(description ="发票种类代码（01-增值税专用发票、03-机动车销售统一发票）(必输)")
    private String invoiceType;

    @Schema(description ="发票代码(必输)")
    private String invoiceCode;

    @Schema(description ="发票号码")
    private String invoiceNumber;

    @Schema(description ="发票页码")
    private Integer invoicePageNumber;

    @Schema(description ="开票日期(格式YYYY/MM/DD)")
    private String invoiceDate;

    @Schema(description ="不含税金额(必输)")
    private BigDecimal taxFreeAmount;

    @Schema(description ="购方税号")
    private String buyerTaxId;

    @Schema(description ="项目属性(0-不动产;1-动产;2-其它)")
    private String projectProperties;

    @Schema(description ="红字信息表号")
    private String tableNumber;

    @Schema(description ="单位")
    private String branch;

    @Schema(description ="导入时间")
    private Date iTime;

    @Schema(description ="导入人员")
    private String personnel;
}
