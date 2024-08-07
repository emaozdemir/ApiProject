package day03;

import base_urls.RestFulBookerBaseUrl;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class C08_QueryParametreleri02 extends RestFulBookerBaseUrl {


    @Test
    public void test01() {
        //    1. Set the URL
        spec.pathParam("first", "booking")
                .queryParam("checkin", "2018-01-01");
        //    2. Set the expected data
        //    3. Send the request and get the response

        //    4. Do Assertion
        given(spec)
                .when()
                .get("{first}")
                .prettyPrint();
    }
}