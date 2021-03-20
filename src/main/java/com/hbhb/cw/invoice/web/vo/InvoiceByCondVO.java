package com.hbhb.cw.invoice.web.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceByCondVO implements Serializable {
    private static final long serialVersionUID = -4668836789952725229L;
    private String invoiceCode;
    private String invoiceNumber;
}
