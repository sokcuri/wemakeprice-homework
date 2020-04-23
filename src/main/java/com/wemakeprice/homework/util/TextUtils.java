package com.wemakeprice.homework.util;

import com.wemakeprice.homework.domain.DivisionText;
import com.wemakeprice.homework.enums.TextType;

import java.util.Comparator;
import java.util.function.BinaryOperator;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TextUtils {

    private static BinaryOperator<String> getMinLengthText = (left, right) ->
            BinaryOperator.minBy(Comparator.comparingInt(String::length)).apply(left, right);
    private static BinaryOperator<String> getMaxLengthText = (left, right) ->
            BinaryOperator.maxBy(Comparator.comparingInt(String::length)).apply(left, right);

    public static String getMixedText(String alphabetText, String numericText) {
        int minLength = getMinLengthText.apply(alphabetText, numericText).length();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < minLength; i++) {
            sb.append(alphabetText.charAt(i)).append(numericText.charAt(i));
        }

        String remainderText = getMaxLengthText.apply(alphabetText, numericText).substring(minLength);
        return sb.append(remainderText).toString();
    }

    public static DivisionText getUnit(String mergedText, int outputUnitCount) {
        int mod = mergedText.length() % outputUnitCount;
        int quotaRange = mergedText.length() - mod;

        return DivisionText.builder()
                .quota(mergedText.substring(0, quotaRange))
                .remainder(mergedText.substring(quotaRange))
                .build();
    }

    public static String getSortedText(String text, TextType textType) {
        return getSortedStream(
                Pattern.compile("").splitAsStream(text),
                textType
        ).collect(Collectors.joining());
    }

    private static Stream<String> getSortedStream(Stream<String> textStream, TextType textType) {
        return textType.getSortedStream(textStream);
    }
}
