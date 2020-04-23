package com.wemakeprice.homework.util;

import com.wemakeprice.homework.enums.ParseOption;
import com.wemakeprice.homework.enums.RegexEnum;
import org.jsoup.nodes.Document;

public class CrawlingUtils {
    public static String getParsedText(Document document, ParseOption option) {
        String html = document.html();
        return option == ParseOption.HTML ?
                RegexEnum.REMOVE_HTML.getMatchedText(html) :
                html;
    }
}
