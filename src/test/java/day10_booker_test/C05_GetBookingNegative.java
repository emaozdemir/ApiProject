package day10_booker_test;

import base_urls.RestFulBookerBaseUrl;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static day10_booker_test.C01_CreateBooking.bookingId;
import static io.restassured.RestAssured.given;

public class C05_GetBookingNegative extends RestFulBookerBaseUrl {

    @Test(dependsOnMethods = {"day10_booker_test.C01_CreateBooking.createBookingTest","day10_booker_test.C04_DeleteBooking.deleteBookingTest"})
    public void getBookingTest(){
        // Set Url
        spec.pathParams("first","booking",
                "second",bookingId);

        // Set expected data // Zaten createBooking classında payload var.
        String expectedData = "Not Found";
        // Send Request And Get Response
        Response response = given(spec).get("{first}/{second}");
        response.prettyPrint();

        // Do Assertions
        String actualData = response.asString();
        Assert.assertEquals(response.statusCode(),404);
        Assert.assertEquals(actualData,expectedData);
}
}
