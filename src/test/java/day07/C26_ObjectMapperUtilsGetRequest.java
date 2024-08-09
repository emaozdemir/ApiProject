package day07;

import base_urls.RestFulBookerBaseUrl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;
import utilities.ObjectMapperUtils;


import java.util.Map;

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
                ,"second",29);

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
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Object> expectedData = ObjectMapperUtils.convertJsonStrToJava(expectedStr, Map.class);














    }


}
