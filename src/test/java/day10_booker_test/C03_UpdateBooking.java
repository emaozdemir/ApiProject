package day10_booker_test;

import base_urls.RestFulBookerBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.BookingPojo;
import utilities.BookingResponseValidator;
import utilities.ObjectMapperUtils;

import static day10_booker_test.C01_CreateBooking.bookingId;
import static day10_booker_test.C01_CreateBooking.payload;
import static io.restassured.RestAssured.given;

public class C03_UpdateBooking extends RestFulBookerBaseUrl {

    @Test(dependsOnMethods = "day10_booker_test.C01_CreateBooking.createBookingTest")
    public void updateBookingTest(){
        spec.pathParams("first","booking",
                "second",bookingId);

        // Set Expected Data
        System.out.println("payload = " + payload);
        payload.setFirstname("Selim");
        payload.setLastname("Brown");
        payload.setAdditionalneeds("Extra Pillow");
        System.out.println("payload = " + payload);

        // Send Request and Get Response
        Response response = given(spec)
                .body(payload)
                .when()
                .put("{first}/{second}");

        response.prettyPrint();

        // Do Assertions

        BookingPojo actualData = ObjectMapperUtils.convertJsonStrToJava(response.asString(), BookingPojo.class);

        BookingResponseValidator.validateResponse(actualData,payload);
}
}
