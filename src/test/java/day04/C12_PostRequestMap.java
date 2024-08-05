package day04;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class C12_PostRequestMap extends JsonPlaceHolderBaseUrl {
            /*
     Given
       1) https://jsonplaceholder.typicode.com/todos
       2)  {
             "userId": 56,
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
    public void test(){
        // Set Url
        spec.pathParam("first","todos");

        // Set Expected Data
        Map<String, Object> payload = new HashMap<>();
        payload.put("userId",21);
        payload.put("title","Tidy your apartment");
        payload.put("completed",false);

        // Send Request get Response
        Response response = given(spec)
                .body(payload)  // Serialization --> Java Objesini Json formatına dönüştürme işlemine denir
                .when()        // Serialization için "Please put Jackson (Databind), Gson, Johnzon, or Yasson in the classpath"
                .post("{first}");
        response.prettyPrint();

        // Do Assertion
        response
                .then()
                .statusCode(201)
                .body("userId",equalTo(payload.get("userId")))
                .body("title",equalTo(payload.get("title")))
                .body("completed",equalTo(payload.get("completed")));


        // Alternatif olarak responsu Map olarak dönüştürebiliriz
        Map<String,Object> actualData = response.as(Map.class); // De-Serialization : Json Objesini Java Objesine çevirme işlemine denir

        Assert.assertEquals(actualData.get("userId"),payload.get("userId"));
        Assert.assertEquals(actualData.get("title"),payload.get("title"));
        Assert.assertEquals(actualData.get("completed"),payload.get("completed"));
    }
}