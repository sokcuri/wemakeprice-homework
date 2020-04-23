package com.wemakeprice.homework.enums;

import java.util.function.Function;
import java.util.stream.Stream;

public enum TextType {
    ALPHABET(text -> text.sorted().sorted(String.CASE_INSENSITIVE_ORDER)),
    NUMERIC(Stream::sorted);

    private Function<Stream<String>, Stream<String>> getSortedStreamExpression;

    TextType(Function<Stream<String>, Stream<String>> getSortedStreamExpression) {
        this.getSortedStreamExpression = getSortedStreamExpression;
    }

    public Stream<String> getSortedStream(Stream<String> textStream) {
        return getSortedStreamExpression.apply(textStream);
    }
}
