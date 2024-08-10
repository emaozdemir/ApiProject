package day08;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojos.JsonPlaceHolderPojo;
import utilities.ObjectMapperUtils;

import java.util.List;

import static io.restassured.RestAssured.given;

public class C28_PojoList extends JsonPlaceHolderBaseUrl {

        /*
        Given
            https://jsonplaceholder.typicode.com/todos
        When
            I send a GET request to the Url
        Then
            HTTP Status Code should be 200
        And
            There must be a todo like:
                {
                    "userId": 1,
                    "id": 4,
                    "title": "et porro tempora",
                    "completed": true
                }
     */

    @Test
    public void test(){
        // Set Url
        spec.pathParam("first","todos");

        // Set Expected Data
        String expectedStr = """
                {
                    "userId": 1,
                    "id": 4,
                    "title": "et porro tempora",
                    "completed": true
                }""";
        System.out.println("expectedStr = " + expectedStr);

        JsonPlaceHolderPojo expectedData = ObjectMapperUtils.convertJsonStrToJava(expectedStr, JsonPlaceHolderPojo.class);
        System.out.println("expectedData = " + expectedData);

        // Sent Request Get Ressponse
        Response response = given(spec).when().get("{first}");
        // response.prettyPrint();

        // Do Assertion
        // 1. Yol
        List<JsonPlaceHolderPojo> actualDataList = response.as(new TypeRef<>() {});
        System.out.println("actualDataList = " + actualDataList);

        int idx = 0;
        for (JsonPlaceHolderPojo eachPojo: actualDataList){
            if (eachPojo.getTitle().equals(expectedData.getTitle())){
                break;
            }
            idx++;
        }
        System.out.println(actualDataList.get(idx));
        JsonPlaceHolderPojo actualData = actualDataList.get(idx);

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(actualData.getUserId(),expectedData.getUserId());
        Assert.assertEquals(actualData.getTitle(),expectedData.getTitle());
        Assert.assertEquals(actualData.getCompleted(),expectedData.getCompleted());

    }
}