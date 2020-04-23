package com.wemakeprice.homework.enums;

public enum ParseOption {
    EXCLUDE_HTML("exclude_html"),
    FULL_TEXT("full_text");

    private String type;

    ParseOption(String type) {
        this.type = type;
    }
}
