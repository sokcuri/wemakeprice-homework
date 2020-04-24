package com.wemakeprice.homework.service;

import com.wemakeprice.homework.domain.CrawlingApiRequest;
import com.wemakeprice.homework.domain.CrawlingApiResponse;
import com.wemakeprice.homework.enums.ParseOption;
import com.wemakeprice.homework.enums.RegexEnum;
import com.wemakeprice.homework.enums.TextType;
import com.wemakeprice.homework.exception.CrawlingFailedException;
import com.wemakeprice.homework.util.CrawlingUtils;
import com.wemakeprice.homework.util.TextUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class Crawler {

    public CrawlingApiResponse execute(CrawlingApiRequest request) throws CrawlingFailedException {
        try {
            Document htmlDocument = Jsoup.connect(request.getUrl()).timeout(5000).get();
            return makeApiResponse(CrawlingUtils.getParsedText(htmlDocument, ParseOption.valueOf(request.getType().toUpperCase())), request.getOutputUnitCount());
        } catch (IOException ioe) {
            throw new CrawlingFailedException(ioe);
        }
    }

    private CrawlingApiResponse makeApiResponse(String content, int outputUnitCount) {
        String alphabetText = RegexEnum.ONLY_ALPHABET.getMatchedText(content);
        String numericText = RegexEnum.ONLY_NUMBER.getMatchedText(content);

        return makeApiResponse(alphabetText, numericText, outputUnitCount);
    }

    private CrawlingApiResponse makeApiResponse(String alphabetText, String numericText, int outputUnitCount) {
        String ascendingSortedAlphabetValue = TextUtils.getSortedAlphabetText(alphabetText);
        String ascendingSortedNumericValue = TextUtils.getSortedNumericText(numericText);
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
