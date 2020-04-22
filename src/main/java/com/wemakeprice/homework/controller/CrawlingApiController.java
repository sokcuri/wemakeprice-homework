package com.wemakeprice.homework.controller;

import com.wemakeprice.homework.domain.CrawlingApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 크롤링 API 메인 컨트롤러
 */
@RestController
@RequestMapping("api")
public class CrawlingApiController {

    @PostMapping("/crawling")
    public ResponseEntity<CrawlingApiResponse> crawling(@RequestBody String url) {
        return ResponseEntity.ok(CrawlingApiResponse.builder().build());
    }
}
