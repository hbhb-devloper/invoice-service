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
public class InvoiceFocusVO implements Serializable {

    private static final long serialVersionUID = 661881007132634300L;

    @Schema(description = "分公司")
    private Integer unitId;

    @Schema(description = "酬金月份")
    private String rewardMonth;

    @Schema(description = "渠道编号")
    private String channelNum;

    @Schema(description = "渠道名称")
    private String channelName;
}
