package com.hbhb.cw.invoice.web.vo;

import com.alibaba.excel.annotation.ExcelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yzc
 * @since 2020-10-19
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceTaxpayerImportVO implements Serializable {

    private static final long serialVersionUID = -6790782029940676439L;

    @ExcelProperty(value = "分公司", index = 0)
    private String unitId;

    @ExcelProperty(value = "渠道编号", index = 1)
    private String channelNum;

    @ExcelProperty(value = "渠道名字", index = 2)
    private String channelName;

    @ExcelProperty(value = "纳税人资质", index = 3)
    private String taxpayerCredentials;

    @ExcelProperty(value = "税率", index = 4)
    private BigDecimal taxRate;

    @ExcelProperty(value = "数据更新时间", index = 5)
    private Date updateTime;

    @ExcelProperty(value = "销售方名称", index = 6)
    private String sellerName;

    @ExcelProperty(value = "销售方纳税人识别号", index = 7)
    private String sellerTaxpayerCode;

    @ExcelProperty(value = "营业执照", index = 8)
    private String businessLicense;

    @ExcelProperty(value = "营业执照纳税人识别号", index = 9)
    private String businessTaxpayerCode;

    @ExcelProperty(value = "法人", index = 10)
    private String legalPerson;
}
