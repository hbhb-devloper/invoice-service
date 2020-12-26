package com.hbhb.cw.invoice.web.vo;

import com.alibaba.excel.annotation.ExcelProperty;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yzc
 * @since 2020-09-15
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceLibraryImportVO implements Serializable {

    private static final long serialVersionUID = -1508099635924526325L;

    @ExcelProperty(value = "序号", index = 0)
    private String lineNumber;

    @ExcelProperty(value = "发票代码", index = 1)
    private String invoiceCode;

    @ExcelProperty(value = "发票号码", index = 2)
    private String invoiceNumber;

    @ExcelProperty(value = "开票日期", index = 3)
    private String invoiceDate;

    @ExcelProperty(value = "发票状态", index = 4)
    private String invoiceState;

    @ExcelProperty(value = "销售方税号", index = 5)
    private String sellerTax;

    @ExcelProperty(value = "销售方名称", index = 6)
    private String sellerName;

    @ExcelProperty(value = "购买方税号", index = 7)
    private String buyerTax;

    @ExcelProperty(value = "购买方名称", index = 8)
    private String buyerName;

    @ExcelProperty(value = "金额", index = 9)
    private String amount;

    @ExcelProperty(value = "税额", index = 10)
    private String taxAmount;

    @ExcelProperty(value = "价税合计", index = 11)
    private String  taxIncludeAmount;

    @ExcelProperty(value = "校验码", index = 12)
    private String checkCode;

    @ExcelProperty(value = "销售方地址、电话", index = 13)
    private String sellerContact;

    @ExcelProperty(value = "销售方开户行及账号", index = 14)
    private String sellerAccount;

    @ExcelProperty(value = "购买方地址、电话", index = 15)
    private String buyerContact;

    @ExcelProperty(value = "购买方开户行及账号", index = 16)
    private String buyerAccount;

    @ExcelProperty(value = "密码区", index = 17)
    private String pwdArea;

    @ExcelProperty(value = "备注", index = 18)
    private String remark;

    @ExcelProperty(value = "开票人", index = 19)
    private String drawer;

    @ExcelProperty(value = "收款人", index = 20)
    private String payee;

    @ExcelProperty(value = "复核人", index = 21)
    private String reviewer;
}
