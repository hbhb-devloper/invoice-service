package com.hbhb.cw.invoice.web.vo;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yzc
 * @since 2020-10-23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceContrastReqVO implements Serializable {

    private static final long serialVersionUID = 7229658142105697449L;

    @Schema(description = "分公司")
    private Integer unitId;

    @Schema(description = "渠道编号")
    private String channelNum;

    @Schema(description = "付款名称")
    private String payName;
}
