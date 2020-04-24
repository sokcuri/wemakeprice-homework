# Homework
URL 주소와 파싱 타입, 출력묶음단위 값을 입력받은 뒤 해당 URL 주소를 크롤링한 html string 결과물을 가지고 지정된 요구사항에 대한 문자열을 추출하는 API 서버 구현

## 사용 버전
* Spring Boot : [2.2.6.RELEASE](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot) (Spring 5.2.5.RELEASE)
* Lombok : [1.18.12](https://mvnrepository.com/artifact/org.projectlombok/lombok)
* AssertJ : [3.15.0](https://mvnrepository.com/artifact/org.assertj/assertj-core)
* Jsoup : [1.13.1](https://mvnrepository.com/artifact/org.jsoup/jsoup)

## API Request Example
```json
{
	"url": "https://naver.com",
	"type": "full_text",
	"outputUnitCount": 1
}
```

## API Response Example
* 정상 케이스
```json
{
  "alphabetValue": "ImateapotHTTPStatusDogsHTTPStatusDogsHypertextTransferProtocolResponsestatuscodesAnddogsInspiredbytheHTTPStatusCatsfromgirliemacImateapotThiscodewasdefinedinasoneofthetraditionalIETFAprilFoolsjokesinRFCHyperTextCoffeePotControlProtocolandisnotexpectedtobeimplementedbyactualHTTPserversHoweverknownimplementationsdoexistCopyrightFrommikeleeorgFormorefunCSSHumorDoggoIpsumWebIpsumJavaScriptBabyBooksHahafunnyrightSharewithyourfriendsTweet",
  "numericValue": "41841819982324202020",
  "mixedValue": "A0A0a0a1a1a1a2a2a2a2a2a3a4a4a4a8a8a8a9a9aaaaaaaBBbbbbbCCCCCCccccccccDDDdddddddddddddEeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeFFFFFfffffffffgggggggggHHHHHHHHHhhhhhhhhIIIIIIiiiiiiiiiiiiiiiiiiiiiJjkkkklllllllllllmmmmmmmmmmmmmmnnnnnnnnnnnnnnnnnnnnnooooooooooooooooooooooooooooooooooooooooooPPPPPPPppppppppppppppRRrrrrrrrrrrrrrrrrrrrrrrrrrrSSSSSSSssssssssssssssssssssssssssssTTTTTTTTTTTTTtttttttttttttttttttttttttttttttttttttuuuuuuuuuuuvvvWwwwwwxxxxyyyyyyyy",
  "ascendingSortedAlphabetValue": "AAaaaaaaaaaaaaaaaaaaaaaaaaaBBbbbbbCCCCCCccccccccDDDdddddddddddddEeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeFFFFFfffffffffgggggggggHHHHHHHHHhhhhhhhhIIIIIIiiiiiiiiiiiiiiiiiiiiiJjkkkklllllllllllmmmmmmmmmmmmmmnnnnnnnnnnnnnnnnnnnnnooooooooooooooooooooooooooooooooooooooooooPPPPPPPppppppppppppppRRrrrrrrrrrrrrrrrrrrrrrrrrrrSSSSSSSssssssssssssssssssssssssssssTTTTTTTTTTTTTtttttttttttttttttttttttttttttttttttttuuuuuuuuuuuvvvWwwwwwxxxxyyyyyyyy",
  "ascendingSortedNumericValue": "00011122222344488899",
  "divisionTextValue": {
    "quota": "A0A0a0a1a1a1a2a2a2a2a2a3a4a4a4a8a8a8a9a9aaaaaaaBBbbbbbCCCCCCccccccccDDDdddddddddddddEeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeFFFFFfffffffffgggggggggHHHHHHHHHhhhhhhhhIIIIIIiiiiiiiiiiiiiiiiiiiiiJjkkkklllllllllllmmmmmmmmmmmmmmnnnnnnnnnnnnnnnnnnnnnooooooooooooooooooooooooooooooooooooooooooPPPPPPPppppppppppppppRRrrrrrrrrrrrrrrrrrrrrrrrrrrSSSSSSSssssssssssssssssssssssssssssTTTTTTTTTTTTTtttttttttttttttttttttttttttttttttttttuuuuuuuuuuuvvvWwwwwwxxxxyyyyyyyy",
    "remainder": ""
  }
}
```

* 실패 케이스 (Exception 발생)
```json
{
  "timestamp": "2020-04-24T07:08:16.130+0000",
  "status": 500,
  "error": "Internal Server Error",
  "message": "크롤링 처리 중 오류가 발생하였습니다. URL을 확인 후 다시 시도해주세요.",
  "path": "/api/crawling"
}
```