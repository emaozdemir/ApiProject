package day07;

import base_urls.RestFulBookerBaseUrl;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ObjectMapperUtils;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class C26_ObjectMapperUtilsGetRequest extends RestFulBookerBaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking/29
        When
            I send GET Request to the url
        Then
            Response body should be like that;
            {
                "firstname": "Jane",
                "lastname": "Doe",
                "totalprice": 111,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2018-01-01",
                    "checkout": "2019-01-01"
                },
                "additionalneeds": "Extra pillows please"
            }
*/
    @Test
    public void test(){
        // Set Url
        spec.pathParams("first","booking"
                ,"second",25);

        // Set Expected Data
        String expectedStr = """
                {
                    "firstname": "Jane",
                    "lastname": "Doe",
                    "totalprice": 111,
                    "depositpaid": true,
                    "bookingdates": {
                        "checkin": "2018-01-01", 
                        "checkout": "2019-01-01"
                    },
                    "additionalneeds": "Extra pillows please"
                }""";
        Map<String,Object> expectedData = ObjectMapperUtils.convertJsonStrToJava(expectedStr, Map.class);
        System.out.println("expectedData.get(\"firstname\") = " + expectedData.get("firstname"));
        System.out.println("expectedData = " + expectedData);

        // Sent Request Get Response
        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();

        // Do Assertions
        Map<String,Object> actualData = ObjectMapperUtils.convertJsonStrToJava(response.asString(), Map.class);

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(actualData.get("firstname"),expectedData.get("firstname"));
        Assert.assertEquals(actualData.get("lastname"),expectedData.get("lastname"));
        Assert.assertEquals(actualData.get("totalprice"),expectedData.get("totalprice"));
        Assert.assertEquals(actualData.get("depositpaid"),expectedData.get("depositpaid"));
        Assert.assertEquals(((Map)actualData.get("bookingdates")).get("checkin"),((Map)expectedData.get("bookingdates")) .get("checkin"));
        Assert.assertEquals(((Map)actualData.get("bookingdates")).get("checkout"),((Map)expectedData.get("bookingdates")) .get("checkout"));
        Assert.assertEquals(actualData.get("additionalneeds"),expectedData.get("additionalneeds"));
    }
}
