package com.wemakeprice.homework.enums;

/**
 * 정규식 사용을 위한 enum class
 */
public enum RegexEnum {

    ONLY_ALPHABET("[^a-zA-Z]*"),

    ONLY_NUMBER("[^0-9]*"),

    REMOVE_HTML("<[^>]*>");

    private String regexPattern;

    RegexEnum(String regexPattern) {
        this.regexPattern = regexPattern;
    }

    public String getMatchedText(String crawlingText) {
        if (crawlingText.matches(regexPattern)) {
            return "";
        }
        return crawlingText.replaceAll(regexPattern, "");
    }
}
