package com.wemakeprice.homework.domain;

import lombok.Builder;
import lombok.Getter;

/**
 * 묶음단위 출력을 위한 몫, 나머지 텍스트 보관용 객체
 */
@Builder
@Getter
public class DivisionText {

    private String quota;

    private String remainder;

}
