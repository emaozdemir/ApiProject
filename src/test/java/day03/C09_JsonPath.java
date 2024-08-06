package day03;

import base_urls.RestFullBookerBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


import java.util.List;
import java.util.Objects;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class C09_JsonPath extends RestFullBookerBaseUrl {
         /*
         zaman gectikce clas calısmayabilir o yuzden postmande once postla creta etmemiz lazım
        Given
            https://restful-booker.herokuapp.com/booking/2
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is “application/json”
        And
            Response body should be like;
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
    public void test01() {
        // Set Url
        spec.pathParams("first", "booking",
                "second", 1569);

        // Set Expected Data

        // Send Request get Response
        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();

        // Do Assertions

        // !. Yol: Hamcrest Matchers ile
        response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstname", equalTo("tim"))
                .body("lastname", equalTo("Brown"))
                .body("totalprice", equalTo(111))
               .body("depositpaid", equalTo(true))
                .body("bookingdates.checkin", equalTo("2025-01-01"))
                .body("bookingdates.checkout", equalTo("2025-02-02"));


        // jsonpath() methodu response Jsonpath classına çevirir. Bu classtan get
        // methodları ile istenilen data kolaylıkla extract edilebilir.

        JsonPath json = response.jsonPath();//responce yi jsanlastırıp degerlerini kullanacağız
        String firstName = json.getString("firstname");
        String lastname = json.getString("lastname");
        int totalprice = json.getInt("totalprice");
        boolean depositpaid = json.getBoolean("depositpaid");
        String checkin = json.getString("bookingdates.checkin");
        String checkout = json.getString("bookingdates.checkout");
        String additionalneeds = json.getString("additionalneeds");

//        // Hard Assertion
        Assert.assertEquals(firstName, "tim");
        Assert.assertEquals(lastname, "Brown");
        Assert.assertEquals(totalprice, 111);
        Assert.assertEquals(depositpaid, true);
        Assert.assertEquals(checkin, "2025-01-01");
        Assert.assertEquals(checkout, "2025-02-02");
        Assert.assertEquals(additionalneeds, "Breakfast");

//        // SoftAssertion
        SoftAssert softAssert= new SoftAssert();

        softAssert.assertEquals(firstName,"tim");
        softAssert.assertEquals(lastname,"Brown");
        softAssert.assertEquals(totalprice,111);
       softAssert.assertEquals(depositpaid,true);
        softAssert.assertEquals(checkin,"2025-01-01");
        softAssert.assertEquals(checkout,"2025-02-02");
        softAssert.assertEquals(additionalneeds,"Breakfast");

        softAssert.assertAll();

        //list olarak alalım
      //  List<Objects> dataList=jsonPath.getList("data.id");
        List<Objects> dataList=json.getList("bookingid");
        System.out.println("dataList = " + dataList);
    }
}