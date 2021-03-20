package com.hbhb.cw.invoice.web.vo;


import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.HeadFontStyle;
import com.alibaba.excel.annotation.write.style.HeadStyle;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
public class InvoiceMachineVO implements Serializable {

    private static final long serialVersionUID = 4552883637693865121L;
    /**
     * 通用机打发票
     */
    @ExcelIgnore
    private Long machineInvoiceId;

    /**
     * 序号
     */
    @ColumnWidth(20)
    @ExcelProperty(value = "序号", index = 0)
    @HeadFontStyle(fontHeightInPoints = 11, fontName = "等线")
    @HeadStyle(fillPatternType = FillPatternType.NO_FILL, wrapped = false,
            borderLeft = BorderStyle.NONE, borderRight = BorderStyle.NONE,
            borderTop = BorderStyle.NONE, borderBottom = BorderStyle.NONE)
    private Integer lineNumber;


    /**
     * 发票种类
     */
    @ColumnWidth(20)
    @ExcelProperty(value = "发票种类", index = 1)
    @HeadFontStyle(fontHeightInPoints = 11, fontName = "等线", color = 2)
    @HeadStyle(fillPatternType = FillPatternType.NO_FILL, wrapped = false,
            borderLeft = BorderStyle.NONE, borderRight = BorderStyle.NONE,
            borderTop = BorderStyle.NONE, borderBottom = BorderStyle.NONE)
    private String invoiceType;

    /**
     * 发票代码（13-桥闸通行费、 14-一二级公路通行费必输）
     */
    @ColumnWidth(50)
    @ExcelProperty(value = "发票代码", index = 2)
    @HeadFontStyle(fontHeightInPoints = 11, fontName = "等线")
    @HeadStyle(fillPatternType = FillPatternType.NO_FILL, wrapped = false,
            borderLeft = BorderStyle.NONE, borderRight = BorderStyle.NONE,
            borderTop = BorderStyle.NONE, borderBottom = BorderStyle.NONE)
    private String invoiceCode;

    /**
     * 发票号码（必输）
     */
    @ColumnWidth(20)
    @ExcelProperty(value = "发票号码", index = 3)
    @HeadFontStyle(fontHeightInPoints = 11, fontName = "等线", color = 2)
    @HeadStyle(fillPatternType = FillPatternType.NO_FILL, wrapped = false,
            borderLeft = BorderStyle.NONE, borderRight = BorderStyle.NONE,
            borderTop = BorderStyle.NONE, borderBottom = BorderStyle.NONE)
    private String invoiceNumber;

    /**
     * 开票日期（必输，格式YYYY/MM/DD）
     */
    @ColumnWidth(50)
    @ExcelProperty(value = "开票日期", index = 4)
    @HeadFontStyle(fontHeightInPoints = 11, fontName = "等线", color = 2)
    @HeadStyle(fillPatternType = FillPatternType.NO_FILL, wrapped = false,
            borderLeft = BorderStyle.NONE, borderRight = BorderStyle.NONE,
            borderTop = BorderStyle.NONE, borderBottom = BorderStyle.NONE,
            horizontalAlignment = HorizontalAlignment.RIGHT)
    @DateTimeFormat(value = "yyyy/MM/dd", use1904windowing = true)
    private Date invoiceDate;

    /**
     * 是否注明旅客身份（填写Y/N：Y-注明旅客身份、N-未注明旅客身份；23-航空电子客票行程单、24-火车票、25-其他车票船票必输）
     */
    @ColumnWidth(50)
    @ExcelProperty(value = "是否注明旅客身份", index = 5)
    @HeadFontStyle(fontHeightInPoints = 11, fontName = "等线")
    @HeadStyle(fillPatternType = FillPatternType.NO_FILL, wrapped = false,
            borderLeft = BorderStyle.NONE, borderRight = BorderStyle.NONE,
            borderTop = BorderStyle.NONE, borderBottom = BorderStyle.NONE)
    private String stateIdentity;

    /**
     * 境内/境外（填写IN/OUT：IN-国内旅客运输服务、OUT-国外旅客运输服务；23-航空电子客票行程单、24-火车票、25-其他车票船票必输）
     */
    @ColumnWidth(50)
    @ExcelProperty(value = "境内/境外", index = 6)
    @HeadFontStyle(fontHeightInPoints = 11, fontName = "等线")
    @HeadStyle(fillPatternType = FillPatternType.NO_FILL, wrapped = false,
            borderLeft = BorderStyle.NONE, borderRight = BorderStyle.NONE,
            borderTop = BorderStyle.NONE, borderBottom = BorderStyle.NONE)
    private String domesticAndForeign;

    /**
     * 不含税金额（21-代扣代缴税收缴款凭证、22-其他可抵扣发票、26-试报账虚拟发票必输）
     */
    @ColumnWidth(50)
    @ExcelProperty(value = "不含税金额", index = 7)
    @HeadFontStyle(fontHeightInPoints = 11, fontName = "等线")
    @HeadStyle(fillPatternType = FillPatternType.NO_FILL, wrapped = false,
            borderLeft = BorderStyle.NONE, borderRight = BorderStyle.NONE,
            borderTop = BorderStyle.NONE, borderBottom = BorderStyle.NONE)
    private BigDecimal taxFreeAmount;

    /**
     * 税码（21-代扣代缴税收缴款凭证、22-其他可抵扣发票、26-试报账虚拟发票必输；范围：0、0.015、0.03、0.05、0.06、0.09、0.1、0.11、0.13、0.16、0.17；）
     */
    @ColumnWidth(50)
    @ExcelProperty(value = "税码", index = 8)
    @HeadFontStyle(fontHeightInPoints = 11, fontName = "等线")
    @HeadStyle(fillPatternType = FillPatternType.NO_FILL, wrapped = false,
            borderLeft = BorderStyle.NONE, borderRight = BorderStyle.NONE,
            borderTop = BorderStyle.NONE, borderBottom = BorderStyle.NONE)
    private String taxCode;

    /**
     * 票价（23-航空电子客票行程单必输）
     */
    @ColumnWidth(50)
    @ExcelProperty(value = "票价", index = 9)
    @HeadFontStyle(fontHeightInPoints = 11, fontName = "等线")
    @HeadStyle(fillPatternType = FillPatternType.NO_FILL, wrapped = false,
            borderLeft = BorderStyle.NONE, borderRight = BorderStyle.NONE,
            borderTop = BorderStyle.NONE, borderBottom = BorderStyle.NONE)
    private BigDecimal fare;

    /**
     * 民航发展基金（23-航空电子客票行程单必输）
     */
    @ColumnWidth(50)
    @ExcelProperty(value = "民航发展基金", index = 10)
    @HeadFontStyle(fontHeightInPoints = 11, fontName = "等线")
    @HeadStyle(fillPatternType = FillPatternType.NO_FILL, wrapped = false,
            borderLeft = BorderStyle.NONE, borderRight = BorderStyle.NONE,
            borderTop = BorderStyle.NONE, borderBottom = BorderStyle.NONE)
    private BigDecimal mhFund;

    /**
     * 燃油附加费（23-航空电子客票行程单必输）
     */
    @ColumnWidth(50)
    @ExcelProperty(value = "燃油附加费", index = 11)
    @HeadFontStyle(fontHeightInPoints = 11, fontName = "等线")
    @HeadStyle(fillPatternType = FillPatternType.NO_FILL, wrapped = false,
            borderLeft = BorderStyle.NONE, borderRight = BorderStyle.NONE,
            borderTop = BorderStyle.NONE, borderBottom = BorderStyle.NONE)
    private BigDecimal ryAdditionalFee;

    /**
     * 其他税费（23-航空电子客票行程单必输）
     */
    @ColumnWidth(50)
    @ExcelProperty(value = "其他税费", index = 12)
    @HeadFontStyle(fontHeightInPoints = 11, fontName = "等线")
    @HeadStyle(fillPatternType = FillPatternType.NO_FILL, wrapped = false,
            borderLeft = BorderStyle.NONE, borderRight = BorderStyle.NONE,
            borderTop = BorderStyle.NONE, borderBottom = BorderStyle.NONE)
    private BigDecimal otherTaxes;

    /**
     * 金额（13-桥闸通行费、 14-一二级公路通行费、16-其他不可抵扣发票、 23-航空电子客票行程单、24-火车票、25-其他车票船票必输）
     */
    @ColumnWidth(50)
    @ExcelProperty(value = "金额", index = 13)
    @HeadFontStyle(fontHeightInPoints = 11, fontName = "等线")
    @HeadStyle(fillPatternType = FillPatternType.NO_FILL, wrapped = false,
            borderLeft = BorderStyle.NONE, borderRight = BorderStyle.NONE,
            borderTop = BorderStyle.NONE, borderBottom = BorderStyle.NONE)
    private BigDecimal money;

    /**
     * 可报账金额（24-火车票必输）
     */
    @ColumnWidth(50)
    @ExcelProperty(value = "可报账金额", index = 14)
    @HeadFontStyle(fontHeightInPoints = 11, fontName = "等线")
    @HeadStyle(fillPatternType = FillPatternType.NO_FILL, wrapped = false,
            borderLeft = BorderStyle.NONE, borderRight = BorderStyle.NONE,
            borderTop = BorderStyle.NONE, borderBottom = BorderStyle.NONE)
    private String reportableAmount;

    /**
     * 购买方税号（必输）
     */
    @ColumnWidth(50)
    @ExcelProperty(value = "购买方税号", index = 15)
    @HeadFontStyle(fontHeightInPoints = 11, fontName = "等线", color = 2)
    @HeadStyle(fillPatternType = FillPatternType.NO_FILL, wrapped = false,
            borderLeft = BorderStyle.NONE, borderRight = BorderStyle.NONE,
            borderTop = BorderStyle.NONE, borderBottom = BorderStyle.NONE)
    private String buyerTaxId;

    /**
     * 购买方名称（必输）
     */
    @ColumnWidth(50)
    @ExcelProperty(value = "购买方名称", index = 16)
    @HeadFontStyle(fontHeightInPoints = 11, fontName = "等线", color = 2)
    @HeadStyle(fillPatternType = FillPatternType.NO_FILL, wrapped = false,
            borderLeft = BorderStyle.NONE, borderRight = BorderStyle.NONE,
            borderTop = BorderStyle.NONE, borderBottom = BorderStyle.NONE)
    private String buyerName;

    /**
     * 销售方税号
     */
    @ColumnWidth(50)
    @ExcelProperty(value = "销售方税号", index = 17)
    @HeadFontStyle(fontHeightInPoints = 11, fontName = "等线")
    @HeadStyle(fillPatternType = FillPatternType.NO_FILL, wrapped = false,
            borderLeft = BorderStyle.NONE, borderRight = BorderStyle.NONE,
            borderTop = BorderStyle.NONE, borderBottom = BorderStyle.NONE)
    private String salesTaxNumber;

    /**
     * 销售方名称
     */
    @ColumnWidth(50)
    @ExcelProperty(value = "销售方名称", index = 18)
    @HeadFontStyle(fontHeightInPoints = 11, fontName = "等线")
    @HeadStyle(fillPatternType = FillPatternType.NO_FILL, wrapped = false,
            borderLeft = BorderStyle.NONE, borderRight = BorderStyle.NONE,
            borderTop = BorderStyle.NONE, borderBottom = BorderStyle.NONE)
    private String sellerName;

}
