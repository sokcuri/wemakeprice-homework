package com.wemakeprice.homework.service;

import com.wemakeprice.homework.domain.CrawlingApiRequest;
import com.wemakeprice.homework.domain.CrawlingApiResponse;
import com.wemakeprice.homework.enums.ParseOption;
import com.wemakeprice.homework.enums.RegexEnum;
import com.wemakeprice.homework.util.CrawlingUtils;
import com.wemakeprice.homework.util.TextUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

@Service
public class Crawler {

    public CrawlingApiResponse execute(CrawlingApiRequest request) {
        Document htmlDocument;
        try {
             htmlDocument = Jsoup.connect(request.getUrl()).get();
        } catch (Exception e) {
            return CrawlingApiResponse.builder().build();
        }
        String content = CrawlingUtils.getParsedText(htmlDocument, ParseOption.valueOf(request.getType().toUpperCase()));

        return CrawlingApiResponse.builder()
                .alphabetValue(RegexEnum.ONLY_ALPHABET.getMatchedText(content))
                .numericValue(RegexEnum.ONLY_NUMBER.getMatchedText(content))
                .ascendingSortedValue(content)
                .mixedValue(content)
                .divisionTextValue(TextUtils.getUnit(content, request.getOutputUnitCount()))
                .build();
    }
}
