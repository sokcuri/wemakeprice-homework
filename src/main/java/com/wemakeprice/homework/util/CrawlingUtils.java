package com.wemakeprice.homework.util;

import com.wemakeprice.homework.enums.ParseOption;
import org.jsoup.nodes.Document;

public class CrawlingUtils {
    public static String getParsedText(Document document, ParseOption option) {
        return option == ParseOption.EXCLUDE_HTML ? document.text() : document.outerHtml();
    }
}
