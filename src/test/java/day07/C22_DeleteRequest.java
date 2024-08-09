package day07;
import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.testng.Assert.assertEquals;

public class C22_DeleteRequest extends JsonPlaceHolderBaseUrl {
    /*
    Given
        https://jsonplaceholder.typicode.com/todos/198
    When
        I send DELETE Request to the Url
    Then
        Status code is 200
        And Response body is { }
*/
    @Test
    public void deleteTest(){ //normalde delete yapınca databaseden silinir ama bu eğitim icin bir yer bu sekilde üstünde oynamalar yapabiliyoruz
        // Set Url
        spec.pathParams("first","todos"
                ,"second",198);
        // Set Expected Data

        Map<String,Object> expectedData = new HashMap<>();

        // Send Request And Get Response
        Response response = given(spec).when().delete("{first}/{second}");
        response.prettyPrint();
        // Do Assertion
        //1.yol
        response
                .then()
                .body(equalTo("{}"));

        // 2. Yöntem
        String responseStr = response.asString();
        Map<String,Object> actualData = response.as(Map.class);
        System.out.println("actualData = " + actualData);
        Assert.assertEquals(responseStr,"{}");

        // 3. Yöntem
        Assert.assertTrue(actualData.isEmpty());//actualData = {}

       // 4. Yöntem
        Assert.assertEquals(actualData.size(),0);
    }
}
