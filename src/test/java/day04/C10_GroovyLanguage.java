package day04;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class C10_GroovyLanguage extends JsonPlaceHolderBaseUrl {
    /*
        Given
           https://jsonplaceholder.typicode.com/todos
        When
           I send GET Request to the URL
        Then
                1)Status code is 200
                2)Print all ids greater than 190 on the console
                Assert that there are 10 ids greater than 190
                3)Print all userIds whose ids are less than 5 on the console
                Assert that the number of userIds whose ids are less than 5 is 4
                4)Print all titles whose ids are less than 5
                Assert that "delectus aut autem" is one of the titles whose id is less than 5
        */
    @Test
    public void test01(){
        // 1. Set the URL
       spec.pathParam("first","todos");
        // 2. Set the expected data
        // 3. Send the request and get the response
        Response response= given(spec).when().get("{first}");
        response.prettyPrint();

        // 4. Do Assertion

    }
}
