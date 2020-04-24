package com.wemakeprice.homework.enums;

import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class TextTypeTest {

    @Test
    public void enum_람다식_테스트() {
        String alphabetText = "asdfasdf";
        String numericText = "1098723456";
        String sortedAlphabetText = TextType.ALPHABET.getSortedStream(Pattern.compile("").splitAsStream(alphabetText)).collect(Collectors.joining());
        String sortedNumericText = TextType.NUMERIC.getSortedStream(Pattern.compile("").splitAsStream(numericText)).collect(Collectors.joining());

        assertThat(sortedAlphabetText).isEqualTo("aaddffss");
        assertThat(sortedNumericText).isEqualTo("0123456789");
    }
}
