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
 * @since 2020-09-12
 */

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class InvoiceAccountImportVO implements Serializable {
    private static final long serialVersionUID = -2439519322521034392L;

    @ExcelProperty(value = "部门", index = 0)
    private String unitName;

    @ExcelProperty(value = "渠道编号", index = 1)
    private String channelNumber;

    @ExcelProperty(value = "渠道名称", index = 2)
    private String channelName;

    @ExcelProperty(value = "业务酬金", index = 3)
    private BigDecimal amount;

    @ExcelProperty(value = "综合补贴", index = 4)
    private BigDecimal subsidy;

    @ExcelProperty(value = "小计", index = 5)
    private BigDecimal subtotal;

    @ExcelProperty(value = "账户", index = 6)
    private String currentAccount;

}
