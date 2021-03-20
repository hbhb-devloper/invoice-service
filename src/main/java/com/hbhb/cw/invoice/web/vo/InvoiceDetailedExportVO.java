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
public class InvoiceDetailedExportVO implements Serializable {

    private static final long serialVersionUID = 3331000794878700636L;

    @ExcelProperty(value = "地市", index = 0)
    private String city;

    @ExcelProperty(value = "县市", index = 1)
    private String  unitId;

    @ExcelProperty(value = "区域营销中心", index = 2)
    private String marketCenter;

    @ExcelProperty(value = "渠道类型", index = 3)
    private String channelType;

    @ExcelProperty(value = "渠道子类型", index = 4)
    private String channelSonType;

    @ExcelProperty(value = "代销商编号", index = 5)
    private String agentNum;

    @ExcelProperty(value = "代销商", index = 6)
    private String agent;

    @ExcelProperty(value = "科目类型", index = 7)
    private String projectType;

    @ExcelProperty(value = "业务类型", index = 8)
    private String businessType;

    @ExcelProperty(value = "品牌", index = 9)
    private String brand;

    @ExcelProperty(value = "平均单价", index = 10)
    private BigDecimal averagePrice;

    @ExcelProperty(value = "计算数量", index = 11)
    private BigDecimal tally;

    @ExcelProperty(value = "酬金金额（含税）", index = 12)
    private BigDecimal ratReward;

    @ExcelProperty(value = "实付金额", index = 13)
    private BigDecimal actuallyPaid;

    @ExcelProperty(value = "计算依据", index = 14)
    private String calculationBasis;

    @ExcelProperty(value = "酬金金额（不含税）", index = 15)
    private BigDecimal reward;

    @ExcelProperty(value = "账期", index = 16)
    private String accountPeriod;
}
