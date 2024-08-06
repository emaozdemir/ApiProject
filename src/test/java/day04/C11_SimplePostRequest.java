package day04;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class C11_SimplePostRequest extends JsonPlaceHolderBaseUrl {
          /*
     Given
       1) https://jsonplaceholder.typicode.com/todos
       2)  {
             "userId": 55,
             "title": "Tidy your room",
             "completed": false
          }
    When
        I send POST Request to the Url

    Then
        Status code is 201
    And
        response body is like {
                                "userId": 55,
                                "title": "Tidy your room",
                                "completed": false,
                                "id": 201
                                }
*/

    @Test
    public void test01() {
        //    1. Set the URL
        spec.pathParam("first", "todos");

        // 2.set expected data //bodyi kurgula demek //databaseye yuklemek için bu yapıyı kurarız.
        String payload = """
                {
                  "userId": 55,
                  "title": "Tidy your room",
                  "completed": false
                }
                """;
        // 3. Send the request and get the response
        Response response = given(spec).body(payload).when().post("{first}");
        response.prettyPrint();
        response
                .then()
                .statusCode(201)
                .body("userId", equalTo(55))
                .body("title", equalTo("Tidy your room"))
                .body("completed", equalTo(false));


    }

}
