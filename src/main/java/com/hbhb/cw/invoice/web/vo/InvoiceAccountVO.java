package com.hbhb.cw.invoice.web.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yzc
 * @since 2020-09-16
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class InvoiceAccountVO implements Serializable {
    private static final long serialVersionUID = 7866024929155534187L;

    private Integer unitId;

    private String unitName;

    private String channelNumber;

    private BigDecimal accountAmount;

    private BigDecimal accountSubsidy;

    private BigDecimal accountSum;
}
