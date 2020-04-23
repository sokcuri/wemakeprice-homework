package com.wemakeprice.homework.service;

import com.wemakeprice.homework.domain.CrawlingApiRequest;
import com.wemakeprice.homework.domain.CrawlingApiResponse;
import com.wemakeprice.homework.enums.ParseOption;
import com.wemakeprice.homework.enums.RegexEnum;
import com.wemakeprice.homework.enums.TextType;
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

        String alphabetText = RegexEnum.ONLY_ALPHABET.getMatchedText(content);
        String numericText = RegexEnum.ONLY_NUMBER.getMatchedText(content);
        return makeApiResponse(alphabetText, numericText, request.getOutputUnitCount());
    }

    private CrawlingApiResponse makeApiResponse(String alphabetText, String numericText, int outputUnitCount) {
        String ascendingSortedAlphabetValue = TextUtils.getSortedText(alphabetText, TextType.ALPHABET);
        String ascendingSortedNumericValue = TextUtils.getSortedText(numericText, TextType.NUMERIC);
        String mixedText = TextUtils.getMixedText(ascendingSortedAlphabetValue, ascendingSortedNumericValue);

        return CrawlingApiResponse.builder()
                .alphabetValue(alphabetText)
                .numericValue(numericText)
                .ascendingSortedAlphabetValue(ascendingSortedAlphabetValue)
                .ascendingSortedNumericValue(ascendingSortedNumericValue)
                .mixedValue(mixedText)
                .divisionTextValue(TextUtils.getUnit(mixedText, outputUnitCount))
                .build();
    }
}
