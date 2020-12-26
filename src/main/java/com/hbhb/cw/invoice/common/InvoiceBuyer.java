package com.hbhb.cw.invoice.common;

/**
 * @author yzc
 * @since 2020-12-26
 */
public enum InvoiceBuyer {
    /**
     * 购方编号
     */
    BUYER_NUMBER("91330000717612557X"),
    /**
     * 购方名称
     */
    BUYER_NAME("中国移动通信集团浙江有限公司杭州分公司"),
    /**
     * type: 16
     */
    TYPE("16"),
    /**
     * attributes: 2
     */
    ATTRIBUTES("2"),
    ;

    private final String  value;

    InvoiceBuyer(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
