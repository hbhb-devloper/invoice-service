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
public class InvoiceMachineAddVO implements Serializable {

    private static final long serialVersionUID = -6670439636458374145L;


    /**
     * 发票种类
     */
    @Schema(description = "发票种类")
    private String invoiceType;

    /**
     * 发票代码（13-桥闸通行费、 14-一二级公路通行费必输）
     */
    @Schema(description = " 发票代码（13-桥闸通行费、 14-一二级公路通行费必输）")
    private String invoiceCode;

    /**
     * 发票号码（必输）
     */
    @Schema(description = "发票号码（必输）")
    private String invoiceNumber;

    /**
     * 开票日期（必输，格式YYYY/MM/DD）
     */
    @Schema(description = "开票日期（必输，格式YYYY/MM/DD）")
    private String invoiceDate;

    /**
     * 是否注明旅客身份（填写Y/N：Y-注明旅客身份、N-未注明旅客身份；23-航空电子客票行程单、24-火车票、25-其他车票船票必输）
     */
    @Schema(description = "是否注明旅客身份（填写Y/N：Y-注明旅客身份、N-未注明旅客身份；23-航空电子客票行程单、24-火车票、25-其他车票船票必输）")
    private String stateIdentity;

    /**
     * 国内/国际（填写IN/OUT：IN-国内旅客运输服务、OUT-国外旅客运输服务；23-航空电子客票行程单、24-火车票、25-其他车票船票必输）
     */
    @Schema(description = "国内/国际（填写IN/OUT：IN-国内旅客运输服务、OUT-国外旅客运输服务；23-航空电子客票行程单、24-火车票、25-其他车票船票必输）")
    private String domesticAndForeign;

    /**
     * 不含税金额（21-代扣代缴税收缴款凭证、22-其他可抵扣发票、26-试报账虚拟发票必输）
     */
    @Schema(description = "不含税金额（21-代扣代缴税收缴款凭证、22-其他可抵扣发票、26-试报账虚拟发票必输）")
    private BigDecimal taxFreeAmount;

    /**
     * 税码（21-代扣代缴税收缴款凭证、22-其他可抵扣发票、26-试报账虚拟发票必输；范围：0、0.015、0.03、0.05、0.06、0.09、0.1、0.11、0.13、0.16、0.17；）
     */
    @Schema(description = "税码（21-代扣代缴税收缴款凭证、22-其他可抵扣发票、26-试报账虚拟发票必输；范围：0、0.015、0.03、0.05、0.06、0.09、0.1、0.11、0.13、0.16、0.17；）\n")
    private String taxCode;

    /**
     * 票价（23-航空电子客票行程单必输）
     */
    @Schema(description = "票价（23-航空电子客票行程单必输）")
    private BigDecimal fare;

    /**
     * 民航发展基金（23-航空电子客票行程单必输）
     */
    @Schema(description = "民航发展基金（23-航空电子客票行程单必输）")
    private BigDecimal mhFund;

    /**
     * 燃油附加费（23-航空电子客票行程单必输）
     */
    @Schema(description = "燃油附加费（23-航空电子客票行程单必输）")
    private BigDecimal ryAdditionalFee;

    /**
     * 其他税费（23-航空电子客票行程单必输）
     */
    @Schema(description = "其他税费（23-航空电子客票行程单必输）")
    private BigDecimal otherTaxes;

    /**
     * 金额（13-桥闸通行费、 14-一二级公路通行费、16-其他不可抵扣发票、 23-航空电子客票行程单、24-火车票、25-其他车票船票必输）
     */
    @Schema(description = "金额（13-桥闸通行费、 14-一二级公路通行费、16-其他不可抵扣发票、 23-航空电子客票行程单、24-火车票、25-其他车票船票必输）\n")
    private BigDecimal money;

    /**
     * 可报账金额（24-火车票必输）
     */
    @Schema(description = "可报账金额（24-火车票必输）")
    private String reportableAmount;

    /**
     * 销售方税号
     */
    @Schema(description = "销售方税号")
    private String salesTaxNumber;

    /**
     * 销售方名称
     */
    @Schema(description = "销售方名称")
    private String sellerName;

}
