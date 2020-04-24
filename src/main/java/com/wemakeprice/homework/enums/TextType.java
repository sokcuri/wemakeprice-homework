package com.wemakeprice.homework.enums;

import java.util.function.Function;
import java.util.stream.Stream;

/**
 * 텍스트 타입에 대한 enum class
 */
public enum TextType {
    ALPHABET(text -> text.sorted().sorted(String.CASE_INSENSITIVE_ORDER)),
    NUMERIC(Stream::sorted);

    private Function<Stream<String>, Stream<String>> getSortedStreamExpression;

    TextType(Function<Stream<String>, Stream<String>> getSortedStreamExpression) {
        this.getSortedStreamExpression = getSortedStreamExpression;
    }

    /**
     * 문자열 스트림을 TextType에 맞는 규칙으로 정렬 후 돌려줍니다.
     * 각 enum의 람다식 (getSortedStreamExpression) 을 이용하여 정렬을 합니다.
     * @param textStream 영문 or 숫자 문자열 스트림
     * @return 정렬된 stream
     */
    public Stream<String> getSortedStream(Stream<String> textStream) {
        return getSortedStreamExpression.apply(textStream);
    }
}
