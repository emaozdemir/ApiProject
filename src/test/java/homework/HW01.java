package homework;

import base_urls.ReqresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class HW01 extends ReqresBaseUrl {
    /*
  Given
      https://reqres.in/api/users/3
  When
      User sends a GET Request to the url
  Then
      HTTP Status Code should be 200
  And
      Content Type should be JSON
  And
      Status Line should be HTTP/1.1 200 OK
*/
    @Test
    public void test01() {
        //    1. Set the URL
        // String url = "https://reqres.in/api/users/3";

        spec.pathParams("first", "users", "second", 3);

        //    2. Set the expected data
        //    3. Send the request and get the response
        //1.yöntem
        //        Response response = given().when().get(url);
        //        response.prettyPrint();
        //2.yöntem
        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();//satırı, dönen yanıtı konsola güzel bir formatta yazdırır.

        //    4. Do Assertion
        response.
                then().
                statusCode(200).
                contentType(ContentType.JSON).
                statusLine("HTTP/1.1 200 OK").
                //extra kendim assertionlar ekledim bodyden
                        body("data.email", equalTo("emma.wong@reqres.in")).
                body("data.first_name", equalTo("Emma")).
                body("data.last_name", equalTo("Wong")).
                body("support.url", equalTo("https://reqres.in/#support-heading")).
                body("support.text", equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));
    }
}
