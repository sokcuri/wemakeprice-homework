package com.wemakeprice.homework.domain;

import com.wemakeprice.homework.enums.ParseOption;
import com.wemakeprice.homework.validator.annotation.EnumValue;
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

    @EnumValue(enumClass = ParseOption.class, message = "유효하지 않은 type입니다.")
    private String type;

    private int outputUnitCount;

}
