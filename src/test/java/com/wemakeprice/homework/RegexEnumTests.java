package com.wemakeprice.homework;

import com.wemakeprice.homework.enums.RegexEnum;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class RegexEnumTests {

    @Test
    public void 정규식_스트림_테스트() {
        String text = "asdf1234asdf";
        Stream<String> matchedTextStream = RegexEnum.ONLY_ALPHABET.getMatchedTextStream(text).sorted();

        assertThat(matchedTextStream.collect(Collectors.joining())).isEqualTo("aaddffss");
    }
}
