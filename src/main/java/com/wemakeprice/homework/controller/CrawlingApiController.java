package com.wemakeprice.homework.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class CrawlingApiController {

    @PostMapping("/crawling")
    public ResponseEntity<?> crawling(@RequestBody String url) {
        return ResponseEntity.ok("");
    }
}
