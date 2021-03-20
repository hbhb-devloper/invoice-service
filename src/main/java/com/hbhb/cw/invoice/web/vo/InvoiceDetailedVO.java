package com.hbhb.cw.invoice.web.vo;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
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
public class InvoiceDetailedVO implements Serializable {

    private static final long serialVersionUID = 2329790960708333453L;

    @Schema(description = "县市")
    private Integer unitId;

    @Schema(description = "代销商编号")
    private String agentNum;

    @Schema(description = "账期")
    private String accountPeriod;
}
