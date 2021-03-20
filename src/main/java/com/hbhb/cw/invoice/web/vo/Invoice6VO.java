package com.hbhb.cw.invoice.web.vo;


import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.HeadFontStyle;
import com.alibaba.excel.annotation.write.style.HeadStyle;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 6%增值税专票对象 invoice_6vat
 *
 * @author ruoyi
 * @date 2020-06-02
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Invoice6VO implements Serializable {

    private static final long serialVersionUID = -2387116808269414891L;
    /**
     * 百分之一增税专票id
     */
    @ExcelIgnore
    private Long vat1Id;

    /**
     * 行号(必输)
     */
    @ColumnWidth(20)
    @ExcelProperty(value = "行号(必输)", index = 0)
    @HeadFontStyle(fontHeightInPoints = 11, bold = false)
    @HeadStyle(fillPatternType = FillPatternType.NO_FILL, wrapped = false,
            borderLeft = BorderStyle.NONE, borderRight = BorderStyle.NONE,
            borderTop = BorderStyle.NONE, borderBottom = BorderStyle.NONE)
    private Integer lineNumber;

    /**
     * 发票种类代码（01-增值税专用发票、03-机动车销售统一发票）(必输)
     */
    @ExcelProperty(value = "发票种类代码（01-增值税专用发票、03-机动车销售统一发票）(必输)", index = 1)
    @HeadFontStyle(fontHeightInPoints = 11, bold = false)
    @HeadStyle(fillPatternType = FillPatternType.NO_FILL, wrapped = false,
            borderLeft = BorderStyle.NONE, borderRight = BorderStyle.NONE,
            borderTop = BorderStyle.NONE, borderBottom = BorderStyle.NONE)
    private String invoiceType;

    /**
     * 发票代码(必输)
     */
    @ExcelProperty(value = "发票代码(必输)", index = 2)
    @HeadFontStyle(fontHeightInPoints = 11, bold = false)
    @HeadStyle(fillPatternType = FillPatternType.NO_FILL, wrapped = false,
            borderLeft = BorderStyle.NONE, borderRight = BorderStyle.NONE,
            borderTop = BorderStyle.NONE, borderBottom = BorderStyle.NONE)
    private String invoiceCode;

    /**
     * 发票号码
     */
    @ExcelProperty(value = "发票号码(必输)", index = 3)
    @HeadFontStyle(fontHeightInPoints = 11, bold = false)
    @HeadStyle(fillPatternType = FillPatternType.NO_FILL, wrapped = false,
            borderLeft = BorderStyle.NONE, borderRight = BorderStyle.NONE,
            borderTop = BorderStyle.NONE, borderBottom = BorderStyle.NONE)
    private String invoiceNumber;

    /**
     * 发票页码
     */
    @ExcelProperty(value = "发票页码", index = 4)
    @HeadFontStyle(fontHeightInPoints = 11, bold = false)
    @HeadStyle(fillPatternType = FillPatternType.NO_FILL, wrapped = false,
            borderLeft = BorderStyle.NONE, borderRight = BorderStyle.NONE,
            borderTop = BorderStyle.NONE, borderBottom = BorderStyle.NONE)
    private Integer invoicePageNumber;

    /**
     * 开票日期(格式YYYY/MM/DD)
     */
    @ExcelProperty(value = "开票日期(格式YYYY/MM/DD)(必输)", index = 5)
    @HeadFontStyle(fontHeightInPoints = 11, bold = false)
    @HeadStyle(fillPatternType = FillPatternType.NO_FILL, wrapped = false,
            borderLeft = BorderStyle.NONE, borderRight = BorderStyle.NONE,
            borderTop = BorderStyle.NONE, borderBottom = BorderStyle.NONE)
    private String invoiceDate;

    /**
     * 不含税金额(必输)
     */
    @ExcelProperty(value = "不含税金额(必输)", index = 6)
    @HeadFontStyle(fontHeightInPoints = 11, bold = false)
    @HeadStyle(fillPatternType = FillPatternType.NO_FILL, wrapped = false,
            borderLeft = BorderStyle.NONE, borderRight = BorderStyle.NONE,
            borderTop = BorderStyle.NONE, borderBottom = BorderStyle.NONE)
    private BigDecimal taxFreeAmount;

    /**
     * 购方税号
     */
    @ExcelProperty(value = "购方税号(必输)", index = 7)
    @HeadFontStyle(fontHeightInPoints = 11, bold = false)
    @HeadStyle(fillPatternType = FillPatternType.NO_FILL, wrapped = false,
            borderLeft = BorderStyle.NONE, borderRight = BorderStyle.NONE,
            borderTop = BorderStyle.NONE, borderBottom = BorderStyle.NONE)
    private String buyerTaxId;

    /**
     * 项目属性(0-不动产;1-动产;2-其它)
     */
    @ExcelProperty(value = "项目属性(0-不动产;1-动产;2-其它)", index = 8)
    @HeadFontStyle(fontHeightInPoints = 11, bold = false)
    @HeadStyle(fillPatternType = FillPatternType.NO_FILL, wrapped = false,
            borderLeft = BorderStyle.NONE, borderRight = BorderStyle.NONE,
            borderTop = BorderStyle.NONE, borderBottom = BorderStyle.NONE)
    private String projectProperties;

    /**
     * 红字信息表号
     */
    @ExcelProperty(value = "红字信息表号", index = 9)
    @HeadFontStyle(fontHeightInPoints = 11, bold = false)
    @HeadStyle(fillPatternType = FillPatternType.NO_FILL, wrapped = false,
            borderLeft = BorderStyle.NONE, borderRight = BorderStyle.NONE,
            borderTop = BorderStyle.NONE, borderBottom = BorderStyle.NONE)
    private String tableNumber;
}
