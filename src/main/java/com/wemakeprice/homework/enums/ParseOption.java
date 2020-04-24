package com.wemakeprice.homework.enums;

/**
 * request 객체의 type 필드를 처리하기 위한 파싱 타입 enum class
 */
public enum ParseOption {
    EXCLUDE_HTML("exclude_html"),
    FULL_TEXT("full_text");

    private String type;

    ParseOption(String type) {
        this.type = type;
    }
}
