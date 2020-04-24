package com.wemakeprice.homework.util;

import com.wemakeprice.homework.domain.DivisionText;
import com.wemakeprice.homework.enums.TextType;

import java.util.Comparator;
import java.util.function.BinaryOperator;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 문자열 가공을 위한 유틸 클래스
 */
public class TextUtils {

    private static BinaryOperator<String> getMinLengthText = (left, right) ->
            BinaryOperator.minBy(Comparator.comparingInt(String::length)).apply(left, right);
    private static BinaryOperator<String> getMaxLengthText = (left, right) ->
            BinaryOperator.maxBy(Comparator.comparingInt(String::length)).apply(left, right);

    /**
     * 영문 문자열과 숫자 문자열을 한글자씩 나눠서 조합하여 돌려줍니다.
     * @param alphabetText 영문만 있는 문자열
     * @param numericText 숫자만 있는 문자열
     * @return 조합된 문자열 (ex : A0A0a1a1B2b2...)
     */
    public static String getMixedText(String alphabetText, String numericText) {
        int minLength = getMinLengthText.apply(alphabetText, numericText).length();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < minLength; i++) {
            sb.append(alphabetText.charAt(i)).append(numericText.charAt(i));
        }

        String remainderText = getMaxLengthText.apply(alphabetText, numericText).substring(minLength);
        return sb.append(remainderText).toString();
    }

    /**
     * 조합된 문자열과 출력묶음단위 값을 받아, 몫 / 나머지 문자열이 들어있는 DivisionText 객체를 돌려줍니다.
     * 출력묶음단위 값이 1인 경우 나머지가 비게 되며, 조합된 문자열 길이보다 단위 값이 큰 경우 몫이 비게 됩니다.
     * @param mergedText 조합된 문자열
     * @param outputUnitCount 출력묶음단위 값
     * @return DivisionText 객체
     */
    public static DivisionText getUnit(String mergedText, int outputUnitCount) {
        int mod = mergedText.length() % outputUnitCount;
        int quotaRange = mergedText.length() - mod;

        return DivisionText.builder()
                .quota(mergedText.substring(0, quotaRange))
                .remainder(mergedText.substring(quotaRange))
                .build();
    }

    /**
     * 영문 문자열을 받아 오름차순 정렬된 영문 문자열로 돌려줍니다.
     * @param text 정렬할 문자열
     * @return 오름차순 정렬된 문자열
     */
    public static String getSortedAlphabetText(String text) {
        return getSortedText(text, TextType.ALPHABET);
    }

    /**
     * 숫자 문자열을 받아 오름차순 정렬된 숫자 문자열로 돌려줍니다.
     * @param text 정렬할 문자열
     * @return 오름차순 정렬된 문자열
     */
    public static String getSortedNumericText(String text) {
        return getSortedText(text, TextType.NUMERIC);
    }

    /**
     * 문자열과 문자열 타입을 받아 정렬 후 돌려줍니다.
     * @param text 정렬할 문자열
     * @param textType 문자열 타입 (ALPHABET, NUMERIC)
     * @return 정렬된 문자열
     */
    private static String getSortedText(String text, TextType textType) {
        return getSortedStream(
                Pattern.compile("").splitAsStream(text),
                textType
        ).collect(Collectors.joining());
    }

    /**
     * 정렬할 문자열의 스트림과 문자열 타입을 받아 정렬 후 돌려줍니다.
     * @param textStream 정렬할 문자열의 스트림
     * @param textType 문자열 타입 (ALPHABET, NUMERIC)
     * @return 정렬된 문자열
     */
    private static Stream<String> getSortedStream(Stream<String> textStream, TextType textType) {
        return textType.getSortedStream(textStream);
    }
}
