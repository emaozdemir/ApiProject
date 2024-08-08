package day06;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojos.JsonPlaceHolderPojo;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class C19_PostRequestPojo extends JsonPlaceHolderBaseUrl {
      /*
     Given
        https://jsonplaceholder.typicode.com/todos
        {
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
    public void pojoTest(){
        // Set Url
        spec.pathParam("first","todos");

        // Set Expected Data
        JsonPlaceHolderPojo payload = new JsonPlaceHolderPojo(55,"Tidy your room",false);
        //eger istersek getle getirebiliriz hatta setle değiliştirebiliriz.
        System.out.println("payload.getUserId() = " + payload.getUserId());
        System.out.println("payload.getTitle() = " + payload.getTitle());
        System.out.println("payload.getCompleted() = " + payload.getCompleted());

        System.out.println("payload = " + payload);

        // Sent request and get response
        Response response = given(spec).body(payload).when().post("{first}");
        response.prettyPrint();

        // Do Assertions:
        response
                .then()
                .statusCode(201)
                .body("userId",equalTo(payload.getUserId()))
                .body("title",equalTo(payload.getTitle()))
                .body("completed",equalTo(payload.getCompleted()));

        Map<String,Object> actualMap = response.as(Map.class);
        Assert.assertEquals(actualMap.get("userId"),payload.getUserId()); // Bu şekilde tavsiye edilmez

        JsonPlaceHolderPojo actualData = response.as(JsonPlaceHolderPojo.class);
        System.out.println("actualData = " + actualData);
        Assert.assertEquals(actualData.getUserId(),payload.getUserId());
        Assert.assertEquals(actualData.getTitle(),payload.getTitle());
        Assert.assertEquals(actualData.getCompleted(),payload.getCompleted());
    }
}