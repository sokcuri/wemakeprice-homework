package com.wemakeprice.homework.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

/**
 * 크롤링 API 리퀘스트용 객체
 */
@Getter
@Setter
public class CrawlingApiRequest {

    @URL
    private String url;

    private String type;

    private int outputUnitCount;

}
