package com.hbhb.cw.invoice.web.vo;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yzc
 * @since 2020-10-21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceAccountRunVO implements Serializable {

    private static final long serialVersionUID = -3498295944267122095L;

    @Schema(description ="分公司")
    private Integer unitId;

    @Schema(description ="渠道编号")
    private String channelNum;

    @Schema(description ="渠道名称")
    private String  channelName;

    @Schema(description ="开始时间")
    private String beginTime;

    @Schema(description ="结束时间")
    private String endTime;
}
