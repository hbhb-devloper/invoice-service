package com.hbhb.cw.invoice.web.vo;

import com.alibaba.excel.annotation.ExcelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yzc
 * @since 2020-09-11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceSubsidyImportVO implements Serializable {
    private static final long serialVersionUID = -6758050599684254079L;

    @ExcelProperty(value = "分公司", index = 0)
    private String unitName;

    @ExcelProperty(value = "渠道编号", index = 1)
    private String channelNumber;

    @ExcelProperty(value = "综合补贴含税", index = 2)
    private BigDecimal taxSubsidy;

}