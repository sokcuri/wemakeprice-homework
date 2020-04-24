package com.wemakeprice.homework.controller;

import com.wemakeprice.homework.domain.CrawlingApiRequest;
import com.wemakeprice.homework.domain.CrawlingApiResponse;
import com.wemakeprice.homework.service.Crawler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 크롤링 API 메인 컨트롤러
 */
@RestController
@RequestMapping("api")
public class CrawlingApiController {

    private Crawler crawler;

    public CrawlingApiController(Crawler crawler) {
        this.crawler = crawler;
    }

    @PostMapping("/crawling")
    public ResponseEntity<CrawlingApiResponse> crawling(@RequestBody @Valid CrawlingApiRequest request) {
        CrawlingApiResponse response = crawler.execute(request);
        return ResponseEntity.ok(response);
    }
}
