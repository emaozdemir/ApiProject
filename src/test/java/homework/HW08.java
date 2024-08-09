package homework;

import base_urls.AutomationExerciseBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class HW08 extends AutomationExerciseBaseUrl {
         /*
    Given
        https://automationexercise.com/api/productsList
    When
        User sends a GET request
    Then
        Assert that the number of "Women" user type is 12
    Note: Print using JsonPath: response.jsonPath().prettyPrint();
        */

    @Test
    public void test01() {
        // Set URL and path parameters
        spec.pathParams("first","productsList");
        // Send request and get response
        Response response = given().spec(spec).when().get("{first}");
        response.prettyPrint();
        JsonPath json = response.jsonPath();// de-serialization-> gelen JSON cevabını Java objesine çevirdik
        List<Integer> idListGroovy = json.getList("products.category.usertype.findAll{it.usertype== 'Women'}.usertype");
        Assert.assertEquals(idListGroovy.size(),12);
        System.out.println("idListGroovy = " + idListGroovy);

    }


}
