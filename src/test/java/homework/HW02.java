package homework;

import base_urls.ReqresBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class HW02 extends ReqresBaseUrl {
    /*
        Given
            https://reqres.in/api/users/23
        When
            User send a GET Request to the url
        Then
            HTTP Status code should be 404
        And
            Status Line should be HTTP/1.1 404 Not Found
        And
            Server is "cloudflare"
        And
            Response body should be empty
     */
    @Test
    public void test01() {
//        //    1. Set the URL
//        // String url = "https://reqres.in/api/users/23";
//        spec.pathParams("first", "users", "second", 23);
//
//        //    2. Set the expected data
//        //    3. Send the request and get the response
//        //1.yöntem
//        //        Response response = given().when().get(url);
//        //        response.prettyPrint();
//
//        //2.yöntem
//        Response response = given(spec).when().get("{first}/{second}");
//        response.prettyPrint();
//        // System.out.println("response.statusCode() = " + response.statusCode());
//
//        //    4. Do Assertion
//        response.
//                then().
//                statusCode(404).
//                contentType(ContentType.JSON).
//                statusLine("HTTP/1.1 404 Not Found").
//                header("Server", "cloudflare").
//                body(equalTo("{}"));
        //     1. Set the URL
        String url = "https://reqres.in/api/users/23";
//    2. Set the expected data
//    3. Send the request and get the response
        Response response = given().when().get(url);
        response.prettyPrint();
//    4. Do Assertion
        response
                .then()
                .statusCode(404)
                .statusLine("HTTP/1.1 404 Not Found")
                .header("Server", equalTo("cloudflare"))
                .body(equalTo("{}"));
    }
}
