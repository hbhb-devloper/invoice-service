package com.hbhb.cw.invoice.common;

/**
 * @author yzc
 * @since 2020-09-22
 */
public enum InvoiceGoodsName {

    /**
     * 代办服务费
     */
    AGENCY_FEE("代办服务费"),
    /**
     * 代理服务费
     */
    AGENCY_SERVICE_FEE("代理服务费"),
    /**
     * *现代服务*代办服务费
     */
    MODERN_SERVICE_FEE("*现代服务*代理服务费"),
    /**
     * 现代服务*代理服务费
     */
    MODERN_SERVICE("*现代服务*代办服务费"),
    /**
     * *其他现代服务*代办服务费
     */
    MODERN_AGENCY_SERVICES("*其他现代服务*代办服务费"),
    /**
     * 其他现代服务*现代服务*代办服务费
     */
    AGENCY_MODERN_SERVICES("*其他现代服务*代理服务费"),
    ;

    private final String  value;

    InvoiceGoodsName(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
