# Homework
URL 주소와 파싱 타입, 출력묶음단위 값을 입력받은 뒤 해당 URL 주소를 크롤링한 html string 결과물을 가지고 지정된 요구사항에 대한 문자열을 추출하는 API 서버 구현

## 사용 버전
* API Server
    * Spring Boot : [2.2.6.RELEASE](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot) (Spring 5.2.5.RELEASE)
    * Lombok : [1.18.12](https://mvnrepository.com/artifact/org.projectlombok/lombok)
    * AssertJ : [3.15.0](https://mvnrepository.com/artifact/org.assertj/assertj-core)
    * Jsoup : [1.13.1](https://mvnrepository.com/artifact/org.jsoup/jsoup)
* Front-end
    * Vue.js : unpkg 버전에 따름 - 2020.04.24 기준 2.6.11 (https://unpkg.com/vue)
    * Axios : unpkg 버전에 따름 - 2020.04.24 기준 0.19.2 (https://unpkg.com/axios/dist/axios.min.js)

## 실행 방법
* IDE 실행
1) `git clone https://github.com/joshua-qa/wemakeprice-homework.git`
2) IDE (IntelliJ 등..) 에서 로드 후 HomeworkApplication 실행
3) 브라우저 실행 후 http://localhost:8080

* Gradle 실행
1) `git clone https://github.com/joshua-qa/wemakeprice-homework.git`
2) clone 해온 폴더로 이동 후 `gradlew clean bootjar`
3) `java -jar ./build/libs/homework-0.0.1-SNAPSHOT.jar` 로 서버 실행
4) 브라우저 실행 후 http://localhost:8080

## API Request Example
* Json Field
    * url : 크롤링을 시도할 URL 입력
    * type : 파싱 타입을 입력
        * `exclude_html` : html 태그를 제거한 텍스트만 추출
        * `full_text` : html 태그를 포함한 모든 텍스트를 추출
    * outputUnitCount : 묶음출력단위 숫자를 입력 (0 불가, 1 이상 입력 가능)
```json
{
	"url": "https://httpstatusdogs.com/418-im-a-teapot",
	"type": "exclude_html",
	"outputUnitCount": 50
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
    "quota": "A0A0a0a1a1a1a2a2a2a2a2a3a4a4a4a8a8a8a9a9aaaaaaaBBbbbbbCCCCCCccccccccDDDdddddddddddddEeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeFFFFFfffffffffgggggggggHHHHHHHHHhhhhhhhhIIIIIIiiiiiiiiiiiiiiiiiiiiiJjkkkklllllllllllmmmmmmmmmmmmmmnnnnnnnnnnnnnnnnnnnnnooooooooooooooooooooooooooooooooooooooooooPPPPPPPppppppppppppppRRrrrrrrrrrrrrrrrrrrrrrrrrrrSSSSSSSssssssssssssssssssssssssssssTTTTTTTTTTTTTtttttttttttttttttttttttttttttttttttttuuuuuuuuuuuvvvWwwwwwxxxxyy",
    "remainder": "yyyyyy"
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