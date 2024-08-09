package day08;

import base_urls.RestFulBookerBaseUrl;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.BookingResponsePojo;
import utilities.ObjectMapperUtils;

import static io.restassured.RestAssured.given;

public class C27_ObjectMapperUtilsPost extends RestFulBookerBaseUrl {
    /*
Given
    1) https://restful-booker.herokuapp.com/booking
    2) {
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
When
    I send POST Request to the Url
Then
    Status code is 200
    And response body should be like
    {
    "bookingid": 2243,
    "booking":{
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
     }
*/
    @Test
    public void test(){
        // Set Url
        spec.pathParam("first","booking");

        // Set Expected Data

        // Sadece POJO kullanırsak:
        BookingDatesPojo bookingDates = new BookingDatesPojo("2018-01-01"
                ,"2019-01-01");

        BookingPojo payload1 = new BookingPojo("Jane"
                ,"Doe"
                ,111
                , true
                ,bookingDates
                ,"Extra pillows please");

        System.out.println("payload1 = " + payload1);

        // ObjectMapper ve Pojoyu beraber kullanırsak

        String payloadStr = """
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
        BookingPojo payload = ObjectMapperUtils.convertJsonStrToJava(payloadStr,BookingPojo.class);
        System.out.println("payload = " + payload);

        // Send Request And Get Response
        Response response = given(spec).body(payload).when().post("{first}");
        response.prettyPrint();

        // Do Assertions
        BookingResponsePojo actualData = ObjectMapperUtils.convertJsonStrToJava(response.asString(), BookingResponsePojo.class);

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(actualData.getBooking().getFirstname(),payload.getFirstname());
        Assert.assertEquals(actualData.getBooking().getLastname(),payload.getLastname());
        Assert.assertEquals(actualData.getBooking().getTotalprice(),payload.getTotalprice());
        Assert.assertEquals(actualData.getBooking().getDepositpaid(),payload.getDepositpaid());
        Assert.assertEquals(actualData.getBooking().getBookingdates().getCheckin(),payload.getBookingdates().getCheckin());
        Assert.assertEquals(actualData.getBooking().getBookingdates().getCheckout(),payload.getBookingdates().getCheckout());
        Assert.assertEquals(actualData.getBooking().getAdditionalneeds(),payload.getAdditionalneeds());
    }

}