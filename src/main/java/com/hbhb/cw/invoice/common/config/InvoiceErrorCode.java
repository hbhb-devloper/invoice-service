package com.hbhb.cw.invoice.common.config;

import lombok.Getter;

/**
 * @author wangxiaogang
 */
@Getter
public enum InvoiceErrorCode {
    INVOICE_ALREADY_EXISTS("80002", "invoice.already.exists"),
    NOT_GET_INVOICE("80003", "not.fet.invoice"),
    INVOICE_NOT_CODE("80004", "invoice.not.code"),
    INVOICE_NOT_IDENTITY("80005", "invoice.not.identity"),
    INVOICE_NOT_AREA("80006", "invoice.not.area"),
    INVOICE_NOT_AMOUNT("80007", "invoice.not.amount"),
    INVOICE_NOT_TAXCODE("80008", "invoice.not.taxcode"),
    INVOICE_NOT_FARE("80009", "invoice.not.fare"),
    INVOICE_NOT_MHFUND("80010", "invoice.not.mhfund"),
    INVOICE_NOT_FEE("80011", "invoice.not.fee"),
    INVOICE_NOT_OTHER("80012", "invoice.not.other"),
    INVOICE_NOT_REPORT_AMOUNT("80013", "invoice.not.report_amount"),
    INVOICE_DATA_IMPORT_ERROR("800065", "invoice.data.import.error"),
    LOCK_OF_INVOICE_ROLE("800066","lock.of.invoice.role"),
    ;


    private String code;

    private String message;

    InvoiceErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
