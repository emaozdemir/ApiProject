package day05;

import base_urls.RestFulBookerBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utilities.RestfulBookerTestData;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class C18_GetRequestNestedMap extends RestFulBookerBaseUrl {

/*
    Given
        https://restful-booker.herokuapp.com/booking/3061
    When
        I send GET Request to the url
    Then
        Status code should be 200
        Response body should be like that;
            {
                "firstname": "John",
                "lastname": "Smith",
                "totalprice": 111,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2018-01-01",
                    "checkout": "2019-01-01"
                },
                "additionalneeds": "Breakfast"
            }
 */

    @Test
    public void getRequestNestedMapTest() {
        //Set the url
        spec.pathParams("first", "booking", "second", 19);

        //Set the expected data
        Map<String, String> bookingDatesMap = RestfulBookerTestData.bookingDatesMapper("2018-01-01", "2019-01-01");
        Map<String, Object> expectedData = RestfulBookerTestData.restfulBookerMapper("John", "Smith", 111, true, bookingDatesMap, "Breakfast");
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given(spec).get("{first}/{second}");
        response.prettyPrint();

        //Do assertion
        Map<String, Object> actualData = response.as(Map.class);
        System.out.println("actualData = " + actualData);

        assertEquals(response.statusCode(), 200);
        assertEquals(actualData.get("firstname"), expectedData.get("firstname"));
        assertEquals(actualData.get("lastname"), expectedData.get("lastname"));
        assertEquals(actualData.get("totalprice"), expectedData.get("totalprice"));
        assertEquals(actualData.get("depositpaid"), expectedData.get("depositpaid"));

        assertEquals(((Map) actualData.get("bookingdates")).get("checkin"), bookingDatesMap.get("checkin"));
        assertEquals(((Map) actualData.get("bookingdates")).get("checkout"), bookingDatesMap.get("checkout"));

        assertEquals(actualData.get("additionalneeds"), expectedData.get("additionalneeds"));


        // methodla assertions
       // verifyResponseData(response.statusCode(), actualData, expectedData, bookingDatesMap);

    }
//    // Method to verify the response data
//    private void verifyResponseData(int statusCode, Map<String, Object> actualData, Map<String, Object> expectedData, Map<String, String> bookingDatesMap) {
//        assertEquals(statusCode, 200);
//        assertEquals(actualData.get("firstname"), expectedData.get("firstname"));
//        assertEquals(actualData.get("lastname"), expectedData.get("lastname"));
//        assertEquals(actualData.get("totalprice"), expectedData.get("totalprice"));
//        assertEquals(actualData.get("depositpaid"), expectedData.get("depositpaid"));
//
//        assertEquals(((Map) actualData.get("bookingdates")).get("checkin"), bookingDatesMap.get("checkin"));
//        assertEquals(((Map) actualData.get("bookingdates")).get("checkout"), bookingDatesMap.get("checkout"));
//
//        assertEquals(actualData.get("additionalneeds"), expectedData.get("additionalneeds"));
//    }
}