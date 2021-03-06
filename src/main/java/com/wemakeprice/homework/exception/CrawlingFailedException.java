package com.wemakeprice.homework.exception;

import java.io.IOException;

/**
 * 크롤링 과정 실패 Exception
 * Jsoup이 던져주는 IOException을 좀 더 명확히하기 위함
 */
public class CrawlingFailedException extends Exception {

    private String message = "크롤링 처리 중 오류가 발생하였습니다. URL을 확인 후 다시 시도해주세요.";

    public CrawlingFailedException(IOException ioe) {
        super();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
