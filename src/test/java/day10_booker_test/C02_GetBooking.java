package day10_booker_test;

import base_urls.RestFulBookerBaseUrl;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static day10_booker_test.C01_CreateBooking.bookingId;
import static io.restassured.RestAssured.given;

public class C02_GetBooking extends RestFulBookerBaseUrl {

    @Test(dependsOnMethods = {"day10_booker_test.C01_CreateBooking.createBookingTest"})
    public void getBookingTest() {
        // Set Url
        spec.pathParams("first", "booking",
                "second", bookingId);

        // Set expected data // Zaten createBooking classında payload var.

        // System.out.println("bookingId = " + bookingId);
        //System.out.println("payload = " + payload);

        // Send Request And Get Response
        Response response = given(spec).get("{first}/{second}");
        response.prettyPrint();
    }
}
