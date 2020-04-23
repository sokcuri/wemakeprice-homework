package com.wemakeprice.homework.enums;

import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * 정규식 사용을 위한 enum class
 */
public enum RegexEnum {

    ONLY_ALPHABET("[^a-zA-Z]+"),

    ONLY_NUMBER("[^0-9]+");

    private String regexPattern;

    RegexEnum(String regexPattern) {
        this.regexPattern = regexPattern;
    }

    public String getMatchedText(String crawlingText) {
        return crawlingText.replaceAll(regexPattern, "");
    }

    public Stream<String> getMatchedTextStream(String crawlingText) {
        return Pattern.compile(regexPattern).splitAsStream(crawlingText).filter(it -> !it.isEmpty());
    }
}
