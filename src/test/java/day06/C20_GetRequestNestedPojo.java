package day06;

import base_urls.JsonPlaceHolderBaseUrl;
import base_urls.RestFulBookerBaseUrl;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojos.JsonPlaceHolderPojo;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class C20_GetRequestNestedPojo extends RestFulBookerBaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking/2586
        When
            I send GET Request to the url
        Then
            Status code should be 200
            Response body should be like that;
            {
        "firstname": "Süleyman",
        "lastname": "Kahve",
        "totalprice": 111,
        "depositpaid": true,
        "bookingdates": {
            "checkin": "2024-01-01",
            "checkout": "2024-02-01"
                        },
        "additionalneeds": "Breakfast"
            }

*/

    @Test
    public void pojoTest(){

    }
}