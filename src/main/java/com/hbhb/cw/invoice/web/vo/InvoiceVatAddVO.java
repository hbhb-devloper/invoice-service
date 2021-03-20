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
public class InvoiceVatAddVO implements Serializable {

    private static final long serialVersionUID = -6719343404343825001L;


    /**
     * 发票种类代码（04-增值税普通发票、10-增值税电子发票、11-增值税普通发票（卷式）、18-增值税电子普通发票(通行费)、13-桥闸通行费、
     * 14-一二级公路通行费、16-其他不可抵扣发票、21-代扣代缴税收缴款凭证、22-其他可抵扣发票、 23-航空电子客票行程单、24-火车票、25-其他车票船票、26-试报账虚拟发票）
     */
    @Schema(description = "发票种类代码（04-增值税普通发票、10-增值税电子发票、11-增值税普通发票（卷式）、18-增值税电子普通发票(通行费)、13-桥闸通行费、 14-一二级公路通行费、16-其他不可抵扣发票、21-代扣代缴税收缴款凭证、22-其他可抵扣发票、 23-航空电子客票行程单、24-火车票、25-其他车票船票、26-试报账虚拟发票）")
    private String invoiceType;

    /**
     * 发票代码
     */
    @Schema(description = "发票代码 ")
    private String invoiceCode;

    /**
     * 发票号码
     */
    @Schema(description = "发票号码  ")
    private String invoiceNumber;

    /**
     * 发票页码
     */
    @Schema(description = "发票页码 ")
    private String invoicePageNumber;

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
     * 红字信息表号
     */
    @Schema(description = "红字信息表号")
    private String tableNumber;

    /**
     * 不含税金额
     */
    @Schema(description = "不含税金额")
    private BigDecimal taxFreeAmount;

}
