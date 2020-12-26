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
public class InvoiceGoodsImportVO implements Serializable {

    private static final long serialVersionUID = 6932279053219473394L;

    @ExcelProperty(value = "序号", index = 0)
    private String  lineNumber;

    @ExcelProperty(value = "发票代码", index = 1)
    private String invoiceCode;

    @ExcelProperty(value = "发票号码", index = 2)
    private String invoiceNumber;

    @ExcelProperty(value = "税收分类编码", index = 3)
    private String taxCode;

    @ExcelProperty(value = "货物或应税劳务名称", index = 4)
    private String goodsName;

    @ExcelProperty(value = "规格型号", index = 5)
    private String model;

    @ExcelProperty(value = "单位", index = 6)
    private String unit;

    @ExcelProperty(value = "数量", index = 7)
    private String  quantity;

    @ExcelProperty(value = "单价", index = 8)
    private String price;

    @ExcelProperty(value = "金额", index = 9)
    private String amount;

    @ExcelProperty(value = "税率", index = 10)
    private String taxRate;

    @ExcelProperty(value = "税额", index = 11)
    private String taxAmount;
}
