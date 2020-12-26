package com.hbhb.cw.invoice.web.vo;


import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.HeadFontStyle;
import com.alibaba.excel.annotation.write.style.HeadStyle;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceVatVO implements Serializable {

    private static final long serialVersionUID = 3765166027584432681L;
    /**
     * 增值税普票id
     */
    @ExcelIgnore
    private Long vatId;

    /**
     * 行号
     */
    @ExcelProperty(value = "行号(必输)", index = 0)
    @HeadFontStyle(fontHeightInPoints = 11, bold = false)
    @HeadStyle(fillPatternType = FillPatternType.NO_FILL, wrapped = false,
            borderLeft = BorderStyle.NONE, borderRight = BorderStyle.NONE,
            borderTop = BorderStyle.NONE, borderBottom = BorderStyle.NONE)
    private Integer lineNumber;

    /**
     * 发票种类代码（04-增值税普通发票、10-增值税电子发票、11-增值税普通发票（卷式）、18-增值税电子普通发票(通行费)、13-桥闸通行费、
     * 14-一二级公路通行费、16-其他不可抵扣发票、21-代扣代缴税收缴款凭证、22-其他可抵扣发票、 23-航空电子客票行程单、24-火车票、25-其他车票船票、26-试报账虚拟发票）
     */
    @ColumnWidth(50)
    @ExcelProperty(value = "发票种类代码（04-增值税普通发票、10-增值税电子发票、11-增值税普通发票（卷式）、18-增值税电子普通发票(通行费)、13-桥闸通行费、 14-一二级公路通行费、16-其他不可抵扣发票、21-代扣代缴税收缴款凭证、22-其他可抵扣发票、 23-航空电子客票行程单、24-火车票、25-其他车票船票、26-试报账虚拟发票)", index = 1)
    @HeadFontStyle(fontHeightInPoints = 11, bold = false)
    @HeadStyle(fillPatternType = FillPatternType.NO_FILL, wrapped = false,
            borderLeft = BorderStyle.NONE, borderRight = BorderStyle.NONE,
            borderTop = BorderStyle.NONE, borderBottom = BorderStyle.NONE)
    private String invoiceType;

    /**
     * 发票代码
     */
    @ColumnWidth(20)
    @ExcelProperty(value = "发票代码", index = 2)
    @HeadFontStyle(fontHeightInPoints = 11, bold = false)
    @HeadStyle(fillPatternType = FillPatternType.NO_FILL, wrapped = false,
            borderLeft = BorderStyle.NONE, borderRight = BorderStyle.NONE,
            borderTop = BorderStyle.NONE, borderBottom = BorderStyle.NONE)
    private String invoiceCode;

    /**
     * 发票号码
     */
    @ColumnWidth(20)
    @ExcelProperty(value = "发票号码", index = 3)
    @HeadFontStyle(fontHeightInPoints = 11, bold = false)
    @HeadStyle(fillPatternType = FillPatternType.NO_FILL, wrapped = false,
            borderLeft = BorderStyle.NONE, borderRight = BorderStyle.NONE,
            borderTop = BorderStyle.NONE, borderBottom = BorderStyle.NONE)
    private String invoiceNumber;

    /**
     * 发票页码
     */
    @ColumnWidth(20)
    @ExcelProperty(value = "发票页码", index = 4)
    @HeadFontStyle(fontHeightInPoints = 11, bold = false)
    @HeadStyle(fillPatternType = FillPatternType.NO_FILL, wrapped = false,
            borderLeft = BorderStyle.NONE, borderRight = BorderStyle.NONE,
            borderTop = BorderStyle.NONE, borderBottom = BorderStyle.NONE)
    private String invoicePageNumber;

    /**
     * 开票日期(格式YYYY/MM/DD)
     */
    @ColumnWidth(20)
    @ExcelProperty(value = "开票日期(格式YYYY/MM/DD)", index = 5)
    @HeadFontStyle(fontHeightInPoints = 11, bold = false)
    @HeadStyle(fillPatternType = FillPatternType.NO_FILL, wrapped = false,
            borderLeft = BorderStyle.NONE, borderRight = BorderStyle.NONE,
            borderTop = BorderStyle.NONE, borderBottom = BorderStyle.NONE)
    private String invoiceDate;

    /**
     * 校验码后六位
     */
    @ColumnWidth(20)
    @ExcelProperty(value = "校验码后六位", index = 6)
    @HeadFontStyle(fontHeightInPoints = 11, bold = false)
    @HeadStyle(fillPatternType = FillPatternType.NO_FILL, wrapped = false,
            borderLeft = BorderStyle.NONE, borderRight = BorderStyle.NONE,
            borderTop = BorderStyle.NONE, borderBottom = BorderStyle.NONE)
    private String checkCode;

    /**
     * 购方税号
     */
    @ColumnWidth(20)
    @ExcelProperty(value = "购方税号", index = 7)
    @HeadFontStyle(fontHeightInPoints = 11, bold = false)
    @HeadStyle(fillPatternType = FillPatternType.NO_FILL, wrapped = false,
            borderLeft = BorderStyle.NONE, borderRight = BorderStyle.NONE,
            borderTop = BorderStyle.NONE, borderBottom = BorderStyle.NONE)
    private String buyerTaxId;

    /**
     * 红字信息表号
     */
    @ColumnWidth(20)
    @ExcelProperty(value = "红字信息表号", index = 8)
    @HeadFontStyle(fontHeightInPoints = 11, bold = false)
    @HeadStyle(fillPatternType = FillPatternType.NO_FILL, wrapped = false,
            borderLeft = BorderStyle.NONE, borderRight = BorderStyle.NONE,
            borderTop = BorderStyle.NONE, borderBottom = BorderStyle.NONE)
    private String tableNumber;
}
