package com.wemakeprice.homework.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wemakeprice.homework.domain.CrawlingApiRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CrawlingApiControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    private ObjectMapper mapper;

    private MediaType contentType;

    @BeforeEach
    public void before() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilter(new CharacterEncodingFilter(StandardCharsets.UTF_8.name(), true))
                .alwaysDo(print())
                .build();

        mapper = new ObjectMapper();

        contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
                MediaType.APPLICATION_JSON.getSubtype(),
                Charset.forName("utf8"));
    }

    @Test
    public void 컨트롤러_성공_케이스() throws Exception {
        CrawlingApiRequest request = makeApiRequest("https://httpstatusdogs.com/418-im-a-teapot", "exclude_html", 50);

        ResultActions result = mockMvc.perform(
                post("http://localhost:8080/api/crawling")
                .contentType(contentType)
                .content(mapper.writeValueAsString(request))
        ).andDo(print());

        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.alphabetValue").exists())
                .andExpect(jsonPath("$.numericValue").exists())
                .andExpect(jsonPath("$.mixedValue").exists())
                .andExpect(jsonPath("$.ascendingSortedAlphabetValue").exists())
                .andExpect(jsonPath("$.ascendingSortedNumericValue").exists())
                .andExpect(jsonPath("$.divisionTextValue.quota").exists())
                .andExpect(jsonPath("$.divisionTextValue.remainder").exists());
    }

    @Test
    public void 컨트롤러_실패_예외_케이스() throws Exception {
        CrawlingApiRequest request = makeApiRequest("http://naver", "full_text", 1);

        ResultActions result = mockMvc.perform(
                post("http://localhost:8080/api/crawling")
                        .contentType(contentType)
                        .content(mapper.writeValueAsString(request))
        ).andDo(print());

        result.andExpect(status().is5xxServerError())
                .andExpect(status().reason("크롤링 처리 중 오류가 발생하였습니다. URL을 확인 후 다시 시도해주세요."));
    }

    @Test
    public void 리퀘스트_validator_테스트() throws Exception {
        CrawlingApiRequest request = makeApiRequest("http://naver.com", "full", 1);

        ResultActions result = mockMvc.perform(
                post("http://localhost:8080/api/crawling")
                        .contentType(contentType)
                        .content(mapper.writeValueAsString(request))
        ).andDo(print());

        result.andExpect(status().is4xxClientError())
                .andExpect(
                        (response) -> assertTrue(response.getResolvedException().getClass().isAssignableFrom(MethodArgumentNotValidException.class))
                );
    }

    private CrawlingApiRequest makeApiRequest(String url, String type, int count) {
        CrawlingApiRequest request = new CrawlingApiRequest();

        request.setUrl(url);
        request.setType(type);
        request.setOutputUnitCount(count);

        return request;
    }
}
