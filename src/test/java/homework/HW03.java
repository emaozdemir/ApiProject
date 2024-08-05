package homework;

import base_urls.ReqresBaseUrl;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class HW03 extends ReqresBaseUrl {
    /*
       Given
           https://reqres.in/api/users/2
       When
           User send GET Request to the URL
       Then
           HTTP Status Code should be 200
       And
           Response format should be "application/json"
       And
           "email" is "janet.weaver@reqres.in",
       And
           "first_name" is "Janet"
       And
           "last_name" is "Weaver"
       And
           "text" is "To keep ReqRes free, contributions towards server costs are appreciated!"

           //interfacelerin consactrı yoktur
    */
    @Test
    public void test01(){
//        1. Set the URL
      //  String url = "https://reqres.in/api/users/2";
        spec.pathParams("first","users","second",2);
//        2. Set the expected data
//        3. Send the request and get the response
        Response response=given(spec).when().get("{first}/{second}");
        response.prettyPrint();
//        4. Do Assertion
        response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("data.email",equalTo("janet.weaver@reqres.in"))
                .body("data.first_name",equalTo("Janet"))
                .body("data.last_name",equalTo("Weaver"))
                .body("support.text",equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));
    }
}
