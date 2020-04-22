package com.wemakeprice.homework.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * 크롤링 API 리퀘스트용 객체
 */
@Getter
@Setter
public class CrawlingApiRequest {

    private String url;

    private String type;

    private int outputUnitCount;

}
