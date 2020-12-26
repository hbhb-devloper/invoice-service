package com.hbhb.cw.invoice.web.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yzc
 * @since 2020-09-18
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceInspectionVO implements Serializable {

    private static final long serialVersionUID = 4349405258394557343L;

    @Schema(description = "序号")
    private Integer lineNumber;

    @Schema(description = "发票代码")
    private String invoiceCode;

    @Schema(description = "发票号码")
    private String invoiceNumber;

    @Schema(description = "开票日期")
    private String invoiceDate;

    @Schema(description = "发票状态")
    private String invoiceState;

    @Schema(description = "销售方税号")
    private String sellerTax;

    @Schema(description = "销售方名称")
    private String sellerName;

    @Schema(description = "购买方税号")
    private String buyerTax;

    @Schema(description = "购买方名称")
    private String buyerName;

    @Schema(description = "金额")
    private String amount;

    @Schema(description = "税额")
    private String taxAmount;

    @Schema(description = "价税合计")
    private String  taxIncludeAmount;

    @Schema(description = "校验码")
    private String checkCode;

    @Schema(description = "销售方地址、电话")
    private String sellerContact;

    @Schema(description = "销售方开户行及账号")
    private String sellerAccount;

    @Schema(description = "购买方地址、电话")
    private String buyerContact;

    @Schema(description = "购买方开户行及账号")
    private String buyerAccount;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "开票人")
    private String drawer;

    @Schema(description = "收款人")
    private String payee;

    @Schema(description = "复核人")
    private String reviewer;

    @Schema(description = "货物或应税劳务名称")
    private String goodsName;

    @Schema(description = "规格型号")
    private String model;

     @Schema(description = "单位")
    private String unit;

     @Schema(description = "数量")
    private String  quantity;

     @Schema(description = "单价")
    private String price;

     @Schema(description = "税率")
    private BigDecimal taxRate;

     @Schema(description = "校验情况")
    private String check;
}
