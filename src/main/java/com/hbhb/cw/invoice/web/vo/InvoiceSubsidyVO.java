package com.hbhb.cw.invoice.web.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yzc
 * @since 2020-09-16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceSubsidyVO implements Serializable {
    private static final long serialVersionUID = 391555319620630705L;

    private Integer unitId;

    private String unitName;

    private String channelNumber;

    private BigDecimal taxSubsidy;
}
