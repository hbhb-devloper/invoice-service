package com.hbhb.cw.invoice.web.vo;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通用机打发票对象 invoice_machine
 *
 * @author ruoyi
 * @date 2020-06-02
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceMachineResVO implements Serializable {

    private static final long serialVersionUID = -8299953531950688270L;
    @Schema(description ="通用机打发票id")
    private Long machineInvoiceId;

    @Schema(description ="序号")
    private String lineNumber;

    @Schema(description ="发票种类")
    private String invoiceType;

    @Schema(description ="发票代码（13-桥闸通行费、 14-一二级公路通行费必输）")
    private String invoiceCode;

    @Schema(description ="发票号码（必输）")
    private String invoiceNumber;

    @Schema(description ="开票日期（必输，格式YYYY/MM/DD）")
    private Date invoiceDate;

    @Schema(description ="是否注明旅客身份（填写Y/N：Y-注明旅客身份、N-未注明旅客身份；23-航空电子客票行程单、24-火车票、25-其他车票船票必输）")
    private String stateIdentity;

    @Schema(description ="国内/国际（填写IN/OUT：IN-国内旅客运输服务、OUT-国外旅客运输服务；23-航空电子客票行程单、24-火车票、25-其他车票船票必输）")
    private String domesticAndForeign;

    @Schema(description ="不含税金额（21-代扣代缴税收缴款凭证、22-其他可抵扣发票、26-试报账虚拟发票必输）")
    private BigDecimal taxFreeAmount;

    @Schema(description ="税码（21-代扣代缴税收缴款凭证、22-其他可抵扣发票、26-试报账虚拟发票必输；范围：0、0.015、0.03、0.05、0.06、0.09、0.1、0.11、0.13、0.16、0.17；）")
    private String taxCode;

    @Schema(description ="票价（23-航空电子客票行程单必输）")
    private BigDecimal fare;

    @Schema(description ="民航发展基金（23-航空电子客票行程单必输）")
    private BigDecimal mhFund;

    @Schema(description ="燃油附加费（23-航空电子客票行程单必输）")
    private BigDecimal ryAdditionalFee;

    @Schema(description ="其他税费（23-航空电子客票行程单必输）")
    private BigDecimal otherTaxes;

    @Schema(description ="金额（13-桥闸通行费、 14-一二级公路通行费、16-其他不可抵扣发票、 23-航空电子客票行程单、24-火车票、25-其他车票船票必输）")
    private BigDecimal money;

    @Schema(description ="可报账金额（24-火车票必输）")
    private BigDecimal reportableAmount;

    @Schema(description ="购买方税号（必输）")
    private String buyerTaxId;

    @Schema(description ="购买方名称（必输）")
    private String buyerName;

    @Schema(description ="销售方税号")
    private String salesTaxNumber;

    @Schema(description ="销售方名称")
    private String sellerName;

    @Schema(description ="分公司")
    private String branch;

    @Schema(description ="导入时间")
    private Date iTime;

    @Schema(description ="导入人员")
    private String personnel;
}
