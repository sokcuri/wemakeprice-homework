package com.wemakeprice.homework.util;
import com.wemakeprice.homework.domain.DivisionText;
import com.wemakeprice.homework.enums.RegexEnum;
import com.wemakeprice.homework.enums.TextType;

import java.util.Comparator;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TextUtils {

    private static BinaryOperator<String> getMinLengthText = (left, right) ->
            BinaryOperator.minBy(Comparator.comparingInt(String::length)).apply(left, right);
    private static BinaryOperator<String> getMaxLengthText = (left, right) ->
            BinaryOperator.maxBy(Comparator.comparingInt(String::length)).apply(left, right);

    public static String makeMixedText(String onlyAlphabetText, String onlyNumericText) {
        int minLength = getMinLengthText.apply(onlyAlphabetText, onlyNumericText).length();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < minLength; i++) {
            sb.append(onlyAlphabetText.charAt(i)).append(onlyNumericText.charAt(i));
        }

        String remainderText = getMaxLengthText.apply(onlyAlphabetText, onlyNumericText).substring(minLength);
        return sb.append(remainderText).toString();
    }

    private static String mergeText(String mixedText, String remainderText) {
        return String.join("", mixedText, remainderText);
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
        RegexEnum regexEnum = textType == TextType.ALPHABET ? RegexEnum.ONLY_ALPHABET : RegexEnum.ONLY_NUMBER;

        return getSortedStream(
                regexEnum.getMatchedTextStream(text),
                textType
        ).collect(Collectors.joining());
    }

    private static Stream<String> getSortedStream(Stream<String> textStream, TextType textType) {
        return textType == TextType.ALPHABET ? textStream.sorted().sorted(String.CASE_INSENSITIVE_ORDER) : textStream.sorted();
    }
}
