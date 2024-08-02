package day02;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;

public class C03_HeadersVeBodyAssertion {
/*
   Given
       https://restful-booker.herokuapp.com/booking/0
   When
       User sends a GET Request to the URL
   Then
       HTTP Status code should be 404
   And
       Status Line should be HTTP/1.1 404 Not Found
   And
       Response body contains "Not Found"
   And
       Response body does not contain "Clarusway"
   And
       Server is "Cowboy"
*/

    @Test
    public void HeaderVeBodyTest(){
        //    1. Set the URL
        String url = "https://restful-booker.herokuapp.com/booking/0";
        //    2. Set the expected data
        //    3. Send the request and get the response
        Response response = given().when().get(url);
        response.prettyPrint();
        //    4. Do Assertion
        // Method Chain yöntemiyle
        response.
                then().
                statusCode(404).
               // contentType(ContentType.JSON).//JSON DEĞİL HATA VERİR. soruda gorev yoktu ama alttaki satırla evet text oldugunu dogrulayabilirz.
                contentType(ContentType.TEXT).
                statusLine("HTTP/1.1 404 Not Found").
                body(containsString("Not Found")).
                body(not(containsString("Clarusway"))).
                header("Server","Cowboy");

        // Dataları extract ederek te assertion yapabiliriz
        String responseStr = response.asString();//response yi asString() methodla stringe ceviririz.
        System.out.println("responseStr = " + responseStr);

        Assert.assertEquals(response.statusCode(),404);
        Assert.assertEquals(response.statusLine(),"HTTP/1.1 404 Not Found");
        Assert.assertTrue(responseStr.contains("Not Found"));
        Assert.assertFalse(responseStr.contains("Clarusway"));
        Assert.assertEquals(response.header("Server"),"Cowboy");

    }
}