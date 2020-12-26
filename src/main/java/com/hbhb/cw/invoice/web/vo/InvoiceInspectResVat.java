package com.hbhb.cw.invoice.web.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yzc
 * @since 2020-11-02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceInspectResVat implements Serializable {

    private static final long serialVersionUID = -2714883304793764471L;

    private String invoiceNumber;

    private String invoiceCode;

    private String invoiceDate;
}
