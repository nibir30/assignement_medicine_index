package com.nibir.medicine_index.constants;

public enum FileSizeType {
    BYTE("BYTE"),
    KB("KB"),
    MB("MB"),
    GB("GB");

    public final String value;

    FileSizeType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
