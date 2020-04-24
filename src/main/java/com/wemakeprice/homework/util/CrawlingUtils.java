package com.wemakeprice.homework.util;

import com.wemakeprice.homework.enums.ParseOption;
import org.jsoup.nodes.Document;

/**
 * 크롤링 과정에서 필요한 메서드가 있는 유틸 클래스
 */
public class CrawlingUtils {
    /**
     * Jsoup로 크롤링해온 Document를 request에서 받은 type 기반으로 가공하여 돌려줍니다.
     * @param document Jsoup로 크롤링해온 Document 객체
     * @param option 파싱 옵션 (exclude_html : html 태그 제외, full_text : html 태그 포함)
     * @return 파싱 타입에 맞게 추출한 문자열
     */
    public static String getParsedText(Document document, ParseOption option) {
        return option == ParseOption.EXCLUDE_HTML ? document.text() : document.outerHtml();
    }
}
