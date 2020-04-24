package com.wemakeprice.homework.service;

import com.wemakeprice.homework.domain.CrawlingApiRequest;
import com.wemakeprice.homework.domain.CrawlingApiResponse;
import com.wemakeprice.homework.enums.ParseOption;
import com.wemakeprice.homework.enums.RegexEnum;
import com.wemakeprice.homework.exception.CrawlingFailedException;
import com.wemakeprice.homework.util.TextUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * 크롤러 Service
 */
@Service
public class Crawler {

    /**
     * 컨트롤러에서 전달받은 request를 기반으로 Jsoup가 크롤링을 시도하고, 그 결과를 만들어 돌려줍니다.
     * @param request 컨트롤러에서 전달받은 request
     * @return CrawlingApiResponse
     * @throws CrawlingFailedException
     */
    public CrawlingApiResponse execute(CrawlingApiRequest request) throws CrawlingFailedException {
        try {
            Document htmlDocument = Jsoup.connect(request.getUrl()).timeout(5000).get();
            return makeApiResponse(htmlDocument, request);
        } catch (IOException ioe) {
            throw new CrawlingFailedException(ioe);
        }
    }

    /**
     * Jsoup 크롤링으로 얻어낸 Document를 컨버트 후, 가공한 문자열들이 들어있는 response를 만들어 돌려줍니다.
     * @param document Jsoup Result Document
     * @param request 컨트롤러에서 전달받은 request
     * @return CrawlingApiResponse
     */
    private CrawlingApiResponse makeApiResponse(Document document, CrawlingApiRequest request) {
        String content = TextUtils.getParsedText(document, ParseOption.valueOf(request.getType().toUpperCase()));
        String alphabetText = RegexEnum.ONLY_ALPHABET.getMatchedText(content);
        String numericText = RegexEnum.ONLY_NUMBER.getMatchedText(content);

        return makeApiResponse(alphabetText, numericText, request.getOutputUnitCount());
    }

    /**
     * Document를 컨버트한 문자열에서 추출한 영문 / 숫자 문자열을 이용하여 아래와 같은 문자열을 생성 후, Response를 만들어 돌려줍니다. (Lombok 빌더 사용)
     * - ascendingSortedAlphabetValue : 오름차순 정렬된 영문 문자열
     * - ascendingSortedNumericValue : 오름차순 정렬된 숫자 문자열
     * - mixedValue : 오름차순 정렬된 문자열을 1자씩 조합한 문자열 (ex : A0A0a1a1b2...)
     * - divisionTextValue : 출력묶음단위 값을 기준으로 몫 / 나머지를 잘라 가지고 있는 객체
     *
     * @param alphabetText 영문만 추출해낸 문자열
     * @param numericText 숫자만 추출해낸 문자열
     * @param outputUnitCount 출력묶음단위 값
     * @return CrawlingApiResponse
     */
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
