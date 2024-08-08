package day06;

import base_urls.RestFulBookerBaseUrl;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.BookingResponsePojo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class C21_PostRequestNestedPojo extends RestFulBookerBaseUrl {
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
        And response body should be like {
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
    public void test01(){
        // Set Url
        spec.pathParam("first","booking");

        // Set Expected Data
        BookingDatesPojo bookingDates = new BookingDatesPojo("2018-01-01","2019-01-01");
        BookingPojo payload = new BookingPojo("Jane"
                                             ,"Doe"
                                             ,111
                                             ,true
                                             ,bookingDates
                                             ,"Extra pillows please");

        // Sent Request Get Response
        Response response = given(spec).body(payload).when().post("{first}");
        response.prettyPrint();
        response
                .then()
                .body("booking.firstname",equalTo(payload.getFirstname()))
                .body("booking.lastname",equalTo(payload.getLastname()))
                .body("booking.totalprice",equalTo(payload.getTotalprice()))
                .body("booking.depositpaid",equalTo(payload.getDepositpaid()))
                .body("booking.bookingdates.checkin",equalTo(bookingDates.getCheckin()))
                .body("booking.bookingdates.checkout",equalTo(bookingDates.getCheckout()))
                .body("booking.additionalneeds",equalTo(payload.getAdditionalneeds()));


        BookingResponsePojo actualData = response.as(BookingResponsePojo.class);
        System.out.println("actualData = " + actualData);

        Assert.assertEquals(actualData.getBooking().getFirstname(),payload.getFirstname());
        Assert.assertEquals(actualData.getBooking().getLastname(),payload.getLastname());
        Assert.assertEquals(actualData.getBooking().getTotalprice(),payload.getTotalprice());
        Assert.assertEquals(actualData.getBooking().getDepositpaid(),payload.getDepositpaid());
        Assert.assertEquals(actualData.getBooking().getBookingdates().getCheckin(),bookingDates.getCheckin());
        Assert.assertEquals(actualData.getBooking().getBookingdates().getCheckout(),bookingDates.getCheckout());
        Assert.assertEquals(actualData.getBooking().getAdditionalneeds(),payload.getAdditionalneeds());

    }
}
