package day04;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static utilities.JsonPlaceHolderTestData.jsonPlaceHolderMapper;

public class C13_PostRequestMapTestData extends JsonPlaceHolderBaseUrl {
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
        Map<String, Object> payload = JsonPlaceHolderTestData.jsonPlaceHolderMapper(55,
                        "Tidy your room",
                        false);

        // Send Request get Response
        Response response = given(spec)
                .body(payload)  // Serialization --> Java Objesini Json formatına dönüştürme işlemine denir
                .when()        // Serialization için "Please put Jackson (Databind), Gson, Johnzon, or Yasson in the classpath"
                .post("{first}");
        response.prettyPrint();

        // Do Assertion
        //1.yol assert etmek için
        response
                .then()
                .statusCode(201)
                .body("userId",equalTo(payload.get("userId")))
                .body("title",equalTo(payload.get("title")))
                .body("completed",equalTo(payload.get("completed")));

        //2.yol assert etmek için
        // Alternatif olarak responsu Map olarak dönüştürebiliriz
        Map<String,Object> actualData = response.as(Map.class); // De-Serialization : Json Objesini Java Objesine çevirme işlemine denir

        Assert.assertEquals(actualData.get("userId"),payload.get("userId"));
        Assert.assertEquals(actualData.get("title"),payload.get("title"));
        Assert.assertEquals(actualData.get("completed"),payload.get("completed"));

        /*
        javada her class bir data tipidir ama her data tipi bir clas değildir.primitif datalar gibi onlar bir clas değildir
         */
    }
}