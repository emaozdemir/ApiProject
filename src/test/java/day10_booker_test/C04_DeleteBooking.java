package day10_booker_test;

import base_urls.RestFulBookerBaseUrl;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static day10_booker_test.C01_CreateBooking.bookingId;
import static io.restassured.RestAssured.given;

public class C04_DeleteBooking extends RestFulBookerBaseUrl {

    @Test(dependsOnMethods = "day10_booker_test.C01_CreateBooking.createBookingTest")
    public void deleteBookingTest(){
        spec.pathParams("first","booking"
        ,"second", bookingId);

        String expectedStr = "Created";//jsona cevirecek bir formatta degil donen bir text .

        Response response = given(spec).when().delete("{first}/{second}");
        String actualStr = response.asString();
        Assert.assertEquals(actualStr,expectedStr);
}
}
