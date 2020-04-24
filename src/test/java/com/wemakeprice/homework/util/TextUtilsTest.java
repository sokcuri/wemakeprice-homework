package com.wemakeprice.homework.util;

import com.wemakeprice.homework.domain.DivisionText;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TextUtilsTest {

    private String shortAlphabetText = "asdfFdAatest";
    private String longAlphabetText = "LoremIpsumissimplydummytextoftheprintingandtypesettingindustryLoremIpsumhasbeentheindustrysstandarddummytexteversincetheswhenanunknownprintertookagalleyoftypeandscrambledittomakeatypespecimenbookIthassurvivednotonlyfivecenturiesbutalsotheleapintoelectronictypesettingremainingessentiallyunchangedItwaspopularisedintheswiththereleaseofLetrasetsheetscontainingLoremIpsumpassagesandmorerecentlywithdesktoppublishingsoftwarelikeAldusPageMakerincludingversionsofLoremIpsum";
    private String shortNumericText = "13579086";
    private String longNumericText = "135790864222123";

    @Test
    public void 영문_숫자_한글자씩조합() {
        String sortedAlphabetText = TextUtils.getSortedAlphabetText(shortAlphabetText);
        String sortedNumericText = TextUtils.getSortedNumericText(shortNumericText);

        assertThat(sortedAlphabetText).isEqualTo("AaaddeFfsstt");
        assertThat(sortedNumericText).isEqualTo("01356789");
    }

    @Test
    public void 출력묶음단위_테스트() {
        String sortedAlphabetText = TextUtils.getSortedAlphabetText(longAlphabetText);
        String sortedNumericText = TextUtils.getSortedNumericText(longNumericText);

        String mixedText = TextUtils.getMixedText(sortedAlphabetText, sortedNumericText);
        int outputUnitCount = 50;
        DivisionText unit = TextUtils.getUnit(mixedText, outputUnitCount);

        assertThat(unit.getQuota()).isEqualTo("A0a1a1a2a2a2a2a3a3a4a5a6a7a8a9aaaaaaaaaaaaaabbbbbccccccccccddddddddddddddddeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeffffffggggggggggghhhhhhhhhhhhhhIIIIIIiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiikkkkkkkLLLLLlllllllllllllllllMmmmmmmmmmmmmmmmmmmnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnoooooooooooooooooooooooooPpppppppppppppppppprrrrrrrrrrrrrrrrrrrrrrrrssssssssssssssssssssssssssssssssssssssstttttttttttttttttttttttttttttttttttttttttttuuuuuuuuuuu");
        assertThat(unit.getRemainder()).isEqualTo("uuuuuuvvvvvwwwwwwxxyyyyyyyyyyyyy");
        assertThat(unit.getRemainder()).hasSizeLessThan(outputUnitCount);
    }
}