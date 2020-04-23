package com.wemakeprice.homework.enums;

public enum ParseOption {
    HTML("html"),
    TEXT("text");

    private String type;

    ParseOption(String type) {
        this.type = type;
    }
}
