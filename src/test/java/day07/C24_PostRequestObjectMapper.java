package day07;

import base_urls.JsonPlaceHolderBaseUrl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class C24_PostRequestObjectMapper  extends JsonPlaceHolderBaseUrl {
    /*
         Given
           1) https://jsonplaceholder.typicode.com/todos
           2) {
               "userId": 55,
               "title": "Tidy your room",
               "completed": false
               }


            I send POST Request to the Url
        Then
            Status code is 201
        And
            response body is like
            {
            "userId": 55,
            "title": "Tidy your room",
            "completed": false,
            "id": 201
            }
*/

    @Test
    public void objectMApperTest() throws JsonProcessingException {
        // Set Url
        spec.pathParam("first","todos");

        // Set Expected Data
        String expectedStr = """
                {
                 "userId": 55,
                 "title": "Tidy your room",
                 "completed": false
                 }""";
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Object> payload = mapper.readValue(expectedStr, Map.class);
        Map<String,Object> payload2 = mapper.readValue(expectedStr, new TypeReference<>(){});

        System.out.println("payload = " + payload);

        // Send Request And Get Response
        Response response = given(spec).body(payload).post("{first}");
        response.prettyPrint();

        // Do Assertions
        Map<String,Object> actualData = mapper.readValue(response.asString(), new TypeReference<>() {});
        Assert.assertEquals(response.statusCode(),201);
        Assert.assertEquals(actualData.get("userId"),payload.get("userId"));
        Assert.assertEquals(actualData.get("title"),payload.get("title"));
        Assert.assertEquals(actualData.get("completed"),payload.get("completed"));
    }
}