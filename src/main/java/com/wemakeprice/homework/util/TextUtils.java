package com.wemakeprice.homework.util;

import com.wemakeprice.homework.domain.DivisionText;
import com.wemakeprice.homework.enums.ParseOption;
import com.wemakeprice.homework.enums.TextType;
import org.jsoup.nodes.Document;

import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 문자열 가공을 위한 유틸 클래스
 */
public class TextUtils {

    /**
     * Jsoup로 크롤링해온 Document를 request에서 받은 type 기반으로 가공하여 돌려줍니다.
     * @param document Jsoup로 크롤링해온 Document 객체
     * @param option 파싱 옵션 (exclude_html : html 태그 제외, full_text : html 태그 포함)
     * @return 파싱 타입에 맞게 추출한 문자열
     */
    public static String getParsedText(Document document, ParseOption option) {
        return option == ParseOption.EXCLUDE_HTML ? document.text() : document.outerHtml();
    }

    /**
     * 영문 문자열과 숫자 문자열을 한글자씩 나눠서 조합하여 돌려줍니다.
     * @param alphabetText 영문만 있는 문자열
     * @param numericText 숫자만 있는 문자열
     * @return 조합된 문자열 (ex : A0A0a1a1B2b2...)
     */
    public static String getMixedText(String alphabetText, String numericText) {
        int minLength = Math.min(alphabetText.length(), numericText.length());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < minLength; i++) {
            sb.append(alphabetText.charAt(i)).append(numericText.charAt(i));
        }

        if (alphabetText.length() == numericText.length()) {
            return sb.toString();
        }
        String remainderText = getRemainderText(alphabetText, numericText, minLength);
        return sb.append(remainderText).toString();
    }

    /**
     * 영문 문자열과 숫자 문자열의 길이를 비교하여, 더 긴 쪽의 여분을 잘라내어 돌려줍니다.
     * @param alphabetText 영문만 있는 문자열
     * @param numericText 숫자만 있는 문자열
     * @param minLength getMixedText 메서드에서 구했던 최소 길이 (둘중 작은 쪽의 길이)
     * @return 여분 문자열
     */
    private static String getRemainderText(String alphabetText, String numericText, int minLength) {
        return alphabetText.length() == minLength ? numericText.substring(minLength) : alphabetText.substring(minLength);
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
