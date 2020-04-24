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

    /**
     * 해당 enum의 정규식을 기반으로 매칭된 문자열 스트림을 돌려줍니다.
     * @param crawlingText 매칭을 시도할 문자열
     * @return 매칭이 완료된 string stream
     */
    public Stream<String> getMatchedTextStream(String crawlingText) {
        return Pattern.compile(regexPattern).splitAsStream(crawlingText).filter(it -> !it.isEmpty());
    }
}
