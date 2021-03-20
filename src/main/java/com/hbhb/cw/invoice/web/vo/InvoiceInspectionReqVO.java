package com.hbhb.cw.invoice.web.vo;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yzc
 * @since 2020-09-21
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceInspectionReqVO implements Serializable {

    private static final long serialVersionUID = 5536299944368989587L;

    @Schema(description = "单位id")
    private Integer unitId;

    @Schema(description = "开票时间")
    private String invoiceDate;

    @Schema(description = "销售方税号")
    private String sellerTax;

    @Schema(description = "销售方名称")
    private String sellerName;

    @Schema(description = "账期")
    private String importDate;
}
