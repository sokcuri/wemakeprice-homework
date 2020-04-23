package com.wemakeprice.homework.util;

import com.wemakeprice.homework.enums.TextType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TextUtilsTest {

    private String shortEnglishText = "asdfFdAatest";
    private String longEnglishText = "wwZaBnjtestbbbMALLLLLLLLLLLLpoiiIcef";
    private String shortNumericText = "13579086";
    private String longNumericText = "135790864222123";

    @Test
    public void 영문_숫자_한글자씩조합() {
        String sortedEnglishText = TextUtils.getSortedText(shortEnglishText, TextType.ALPHABET);
        String sortedNumericText = TextUtils.getSortedText(shortNumericText, TextType.NUMERIC);

        assertThat(sortedEnglishText).isEqualTo("AaaddeFfsstt");
    }
}