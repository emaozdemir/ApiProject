package day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class C02_HeaderAsserions {
    /*
     Gherkin diliyle
    Given:pre condition on kosulları anları
        https://restful-booker.herokuapp.com/booking
    When:aksiyonlari belirten assertionlari yapar
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
    Steps to follow in API Testing:
    1. Set the URL
    2. Set the expected data
    3. Send the request and get the response
    4. Do Assertion
    */

    @Test
    public void headerTest() {
        // 1. Set the URL
        String url = "https://restful-booker.herokuapp.com/booking";
        //  2. Set the expected data
        //3. Send the request and get the response
        //4. Do Assertion

       //1.yol
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

      //2.yol
        //  2. Set the expected data

        //3. Send the request and get the response
        Response response=  given().when().get(url);
       // response.prettyPrint();

        //4. Do Assertion   header("Server", "Cowboy");
        //method chain olarak  devam edilebilir.. fluent form
        response.
                then().
                statusCode(200).
                statusLine("HTTP/1.1 200 OK").
                contentType(ContentType.JSON);
       // TestNg Assertionları gibi yöntemler ile de devam edebiliriz.
        int statusCode = response.statusCode();
        Assert.assertEquals(statusCode,200);
        String statusLine = response.statusLine();
        Assert.assertEquals(statusLine,"HTTP/1.1 200 OK");
        String contentType = response.contentType();
        Assert.assertEquals(contentType,"application/json; charset=utf-8");
        String connectionHeader = response.header("Connection");
        Assert.assertEquals(connectionHeader,"keep-alive");


    }

}
