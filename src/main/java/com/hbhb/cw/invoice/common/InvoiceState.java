package com.hbhb.cw.invoice.common;

/**
 * @author yzc
 * @since 2020-09-18
 */
public enum InvoiceState {
    /**
     * 预算科目名称
     */
    BUDGET_NAME("正常"),

    /**
     * 项目名称
     */
    PROJECT_NAME("作废"),
    ;

    private final String value;

    InvoiceState(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
