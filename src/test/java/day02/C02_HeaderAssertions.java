package day02;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class C02_HeaderAssertions {
    /*
     Gherkin language -->(cucumberda ögrendik)

    Given:pre condition on kosulları anlatır
        https://restful-booker.herokuapp.com/booking
    When:aksiyonlar baslar
        User sends a GET Request to the url
    Then assertionlari içeririr
        HTTP Status Code should be 200
    And tekrarlılari bağlar
        Content Type should be JSON
    And
        Status Line should be HTTP/1.1 200 OK
    And
        Print Connection and Date headers on console
    And
        Print all headers on console
*/
  /*
  rehber;
    Steps to follow in API Testing:
    1. Set the URL
    2. Set the expected data
    3. Send the request and get the response
    4. Do Assertion
    */

    @Test
    public void headerTest() {
        //1. Set the URL
        String url = "https://restful-booker.herokuapp.com/booking";
        //2. Set the expected data //atlıyoruz cunku post ve put requestlerde kullanırız daha cok o yuzden atlıyoruz bazen gette kullanılabilir
        //3. Send the request and get the response
        //4. Do Assertion

        // 1. Yol: Assertionlar method chain şeklinde yapılabilir (fluent form tarzı )
        given().
                when().
                get(url).
                then().statusCode(200).
                //   contentType("application/json").
                contentType(ContentType.JSON).
                header("Connection", "keep-alive").//1.yol
                header("Connection", equalTo("keep-alive")).//2.yol
                header("Connection", is("keep-alive")).//3.yol
                statusLine("HTTP/1.1 200 OK");

        // 2. Yol : Basamak basamak ilerlemek... tavsiye edilen yol
        //    2. Set the expected data  -- > daha sonra uygulayacağız.
        //    3. Send the request and get the response
        Response response = given().when().get(url);
        // response.prettyPrint();
        //    4. Do Assertion

        // method chain olarak devam edilebilir...
        response.
                then().
                statusCode(200).
                statusLine("HTTP/1.1 200 OK").
                contentType(ContentType.JSON).
                header("Connection",equalTo("keep-alive")).
                header("Connection","keep-alive").
                header("Connection",is("keep-alive"));
        // TestNg Assertionları gibi yöntemler ile de devam edebiliriz.

        int statusCode = response.statusCode();
        String statusLine = response.statusLine();
        String contentType = response.contentType();
        String conn = response.header("connection");

        Assert.assertEquals(statusCode,200);
        Assert.assertEquals(statusLine,"HTTP/1.1 200 OK");
        Assert.assertEquals(contentType,"application/json; charset=utf-8");
        Assert.assertEquals(conn,"keep-alive");

    }

}
