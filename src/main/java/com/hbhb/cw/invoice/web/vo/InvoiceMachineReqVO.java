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
public class InvoiceMachineReqVO implements Serializable {

    private static final long serialVersionUID = -3056040268152645775L;
    /**
     * 发票代码（13-桥闸通行费、 14-一二级公路通行费必输）
     */
    @Schema(description = "发票代码（13-桥闸通行费、 14-一二级公路通行费必输）")
    private String invoiceCode;

    /**
     * 发票号码（必输）
     */
    @Schema(description = "发票号码（必输）")
    private String invoiceNumber;

    /**
     * 开票日期（必输，格式YYYY/MM/DD）
     */
    @Schema(description = "开票日期")
    private String invoiceDate;

    /**
     * 金额（13-桥闸通行费、 14-一二级公路通行费、16-其他不可抵扣发票、 23-航空电子客票行程单、24-火车票、25-其他车票船票必输）
     */
    @Schema(description = "金额（13-桥闸通行费、 14-一二级公路通行费、16-其他不可抵扣发票、 23-航空电子客票行程单、24-火车票、25-其他车票船票必输）")
    private BigDecimal money;

    /**
     * 购买方税号（必输）
     */
    @Schema(description = "购买方税号（必输）")
    private String buyerTaxId;

    /**
     * 购买方名称（必输）
     */
    @Schema(description = "购买方名称（必输）")
    private String buyerName;

    /**
     * 分公司
     */
    @Schema(description = "分公司")
    private String branch;

    /**
     * 导入时间
     */
    @Schema(description = "导入时间")
    private Date iTime;

    /**
     * 导入人员
     */
    @Schema(description = "导入人员")
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
