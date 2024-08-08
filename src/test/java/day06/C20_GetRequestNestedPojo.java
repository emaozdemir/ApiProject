package day06;

import base_urls.RestFulBookerBaseUrl;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class C20_GetRequestNestedPojo extends RestFulBookerBaseUrl {

    /*
        Given
            https://restful-booker.herokuapp.com/booking/3345
        When
            I send GET Request to the url
        Then
            Status code should be 200
            Response body should be like that;
    {
    "firstname" : "tim",
    "lastname" : "Brown",
    "totalprice" : 111,
    "depositpaid" : true,
    "bookingdates" : {
        "checkin" : "2025-01-01",
        "checkout" : "2025-02-02"
    },
    "additionalneeds" : "Breakfast"
}

*/
    @Test
    public void test01(){
        // Set Url
        spec.pathParams("first","booking"
                ,"second",2414);

        // Set Expected Data ben bu sekilde bir data bekliyorum

        BookingDatesPojo bookingDates = new BookingDatesPojo("2025-01-01","2025-02-02");
        BookingPojo expectedData = new BookingPojo("tim",
                "Brown",
                111,
                true,
                bookingDates,
                "Breakfast");
        System.out.println("expectedData = " + expectedData);

        // Sent Request and get Response
        Response response = given(spec).when().get("{first}/{second}");

        // Do Assertions

        //1.yontem body assertion
        response
                .then()
                .body("bookingdates.checkin", equalTo(expectedData.getBookingdates().getCheckin()))
                .body("bookingdates.checkin", equalTo(bookingDates.getCheckin()));

        //2. yontem map assertion
        BookingPojo actualData = response.as(BookingPojo.class);//pojo clasından
        Assert.assertEquals(actualData.getFirstname(),expectedData.getFirstname());
        Assert.assertEquals(actualData.getLastname(),expectedData.getLastname());
        Assert.assertEquals(actualData.getDepositpaid(),expectedData.getDepositpaid());
        Assert.assertEquals(actualData.getTotalprice(),expectedData.getTotalprice());
        Assert.assertEquals(actualData.getBookingdates().getCheckin(),bookingDates.getCheckin());
        Assert.assertEquals(actualData.getBookingdates().getCheckout(),bookingDates.getCheckout());
        Assert.assertEquals(actualData.getAdditionalneeds(),expectedData.getAdditionalneeds());

    }
}