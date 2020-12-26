package com.hbhb.cw.invoice.common;


/**
 * @author yzc
 */

public enum InvoiceMachineType {

    THIRTEEN("13"),

    FOURTEEN("14"),

    TWENTY_THREE("23"),

    TWENTY_FOUR("24"),

    TWENTY_FIVE("25"),

    TWENTY_ONE("21"),

    TWENTY_TWO("22"),

    TWENTY_SIX("26"),
    ;

    public String getValue() {
        return value;
    }

    private final String value;

    InvoiceMachineType(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
