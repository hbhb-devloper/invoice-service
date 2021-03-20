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
public class InvoiceVatResVO implements Serializable {

    private static final long serialVersionUID = -2683520150800166156L;
    @Schema(description ="id")
    private Long vatId;

    @Schema(description ="行号")
    private String lineNumber;

    @Schema(description ="发票种类代码（04-增值税普通发票、10-增值税电子发票、11-增值税普通发票（卷式）、18-增值税电子普通发票(通行费)、13-桥闸通行费、 14-一二级公路通行费、16-其他不可抵扣发票、21-代扣代缴税收缴款凭证、22-其他可抵扣发票、 23-航空电子客票行程单、24-火车票、25-其他车票船票、26-试报账虚拟发票）")
    private String invoiceType;

    @Schema(description ="发票代码")
    private String invoiceCode;

    @Schema(description ="发票号码")
    private String invoiceNumber;

    @Schema(description ="发票页码")
    private String invoicePageNumber;

    @Schema(description ="开票日期")
    private String invoiceDate;

    @Schema(description ="校验码后六位")
    private String checkCode;

    @Schema(description ="购方税号")
    private String buyerTaxId;

    @Schema(description ="红字信息表号")
    private String tableNumber;

    @Schema(description ="不含税金额")
    private BigDecimal taxFreeAmount;

    @Schema(description ="分公司")
    private String branch;

    @Schema(description ="导入时间")
    private Date iTime;

    @Schema(description ="导入人员")
    private String personnel;


}
