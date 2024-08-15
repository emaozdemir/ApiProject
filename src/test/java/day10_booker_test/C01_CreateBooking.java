package day10_booker_test;

import base_urls.RestFulBookerBaseUrl;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pojos.BookingPojo;
import pojos.BookingResponsePojo;
import utilities.BookingResponseValidator;
import utilities.ObjectMapperUtils;

import static io.restassured.RestAssured.given;

public class C01_CreateBooking extends RestFulBookerBaseUrl {

    public static Integer bookingId;  // public --> projede herhangi bir yerden ulaşılacak, baska packagelerden ulasırım
    // static --> object create etmeye gerek kalmayacak just import
    public static BookingPojo payload;
    @Test
    public void createBookingTest(){
        // Set Url
        spec.pathParam("first","booking");

        // Set Expected Date
        String payloadStr = """
                {
                    "firstname" : "Süleyman",
                    "lastname" : "Kahve",
                    "totalprice" : 111,
                    "depositpaid" : true,
                    "bookingdates" : {
                        "checkin" : "2024-01-01",
                        "checkout" : "2024-02-01"
                    },
                    "additionalneeds" : "Breakfast"
                }""";

        payload = ObjectMapperUtils.convertJsonStrToJava(payloadStr, BookingPojo.class);

        // Sent Request and Get Response
        Response response = given(spec).
                body(payload).
                when().
                post("{first}");

        response.prettyPrint();

        // Do Assertions
        BookingResponsePojo actualData = ObjectMapperUtils.convertJsonStrToJava(response.asString(), BookingResponsePojo.class);

//        Assert.assertEquals(response.statusCode(),200);
//        Assert.assertEquals(actualData.getBooking().getFirstname(),payload.getFirstname());
//        Assert.assertEquals(actualData.getBooking().getLastname(),payload.getLastname());
//        Assert.assertEquals(actualData.getBooking().getTotalprice(),payload.getTotalprice());
//        Assert.assertEquals(actualData.getBooking().getDepositpaid(),payload.getDepositpaid());
//        Assert.assertEquals(actualData.getBooking().getBookingdates().getCheckin(),payload.getBookingdates().getCheckin());
//        Assert.assertEquals(actualData.getBooking().getBookingdates().getCheckout(),payload.getBookingdates().getCheckout());
//        Assert.assertEquals(actualData.getBooking().getAdditionalneeds(),payload.getAdditionalneeds());


        BookingResponseValidator.validateResponse(actualData.getBooking(),payload);

        bookingId = actualData.getBookingid();

//        System.out.println("bookingId = " + bookingId);
//        System.out.println("payload = " + payload);
    }
}
