package com.wemakeprice.homework.domain;

import lombok.Builder;
import lombok.Getter;

/**
 * 크롤링 API 리스폰스용 객체
 */
@Builder
@Getter
public class CrawlingApiResponse {

    private String alphabetValue;

    private String numericValue;

    private String mixedValue;

    private String ascendingSortedValue;

    private DivisionText divisionTextValue;

}
