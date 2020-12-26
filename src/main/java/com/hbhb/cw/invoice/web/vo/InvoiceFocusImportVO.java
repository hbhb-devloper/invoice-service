package com.hbhb.cw.invoice.web.vo;

import com.alibaba.excel.annotation.ExcelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yzc
 * @since 2020-10-22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceFocusImportVO implements Serializable {

    private static final long serialVersionUID = -3498295944267122095L;

    @ExcelProperty(value = "酬金月份", index = 0)
    private String rewardMonth;

    @ExcelProperty(value = "地市", index = 1)
    private String city;

    @ExcelProperty(value = "县市", index = 2)
    private String unitId;

    @ExcelProperty(value = "片区", index = 3)
    private String area;

    @ExcelProperty(value = "渠道编号", index = 4)
    private String channelNum;

    @ExcelProperty(value = "渠道名称", index = 5)
    private String channelName;

    @ExcelProperty(value = "一级类别", index = 6)
    private String firstLevel;

    @ExcelProperty(value = "二级类别", index = 7)
    private String secondLevel;

    @ExcelProperty(value = "三级类别", index = 8)
    private String thirdLevel;

    @ExcelProperty(value = "负责人", index = 9)
    private String director;

    @ExcelProperty(value = "代扣代缴", index = 10)
    private Long withhold;

    @ExcelProperty(value = "付款名称", index = 11)
    private String payName;

    @ExcelProperty(value = "开户行", index = 12)
    private String bank;

    @ExcelProperty(value = "开户支行", index = 13)
    private String bankBranch;

    @ExcelProperty(value = "银行账号", index = 14)
    private String bankAccount;

    @ExcelProperty(value = "酬金（元）", index = 15)
    private BigDecimal reward;

    @ExcelProperty(value = "应付（元）", index = 16)
    private BigDecimal payable;

    @ExcelProperty(value = "支付状态", index = 17)
    private String payState;

    @ExcelProperty(value = "开票信息", index = 18)
    private String invoiceOpen;

    @ExcelProperty(value = "酬金发票编号", index = 19)
    private String rewardNumber;

    @ExcelProperty(value = "用途", index = 20)
    private String purpose;
}
