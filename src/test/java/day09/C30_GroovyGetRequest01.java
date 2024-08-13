package day09;

import base_urls.GorestBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class C30_GroovyGetRequest01 extends GorestBaseUrl {
       /*
            Given
                https://gorest.co.in/public/v1/users
            When
                User send GET Request
            Then
                The value of "pagination limit" is 10
            And
                The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
            And
                The number of users should  be 10
            And
                We have at least one "active" status
            And
                Aamod Devar, Acharyasuta Johar, Girindra Pilla, Purnima Joshi DVM are among the users -> This may change
            And
                The female users are less than or equals to male users -> This may change
       */

    @Test
    public void groovyTest(){
        spec.pathParams("first","users");

        // Send Request And Get Response
        Response response = given(spec).when().get("{first}");
         response.prettyPrint();
         // Do Assertion
        response.then()
                .statusCode(200)
                .body("meta.pagination.limit", equalTo(10))
                .body("meta.pagination.links.current", equalTo("https://gorest.co.in/public/v1/users?page=1"))
                .body("data.id", hasSize(10)) // Check that the number of users is 10
                .body("data.status", hasItem("active")) // At least one user is active
                .body("data.name", hasItems("Sweta Naik", "Devani Agarwal", "Apsara Gandhi")); // Verify specific users
//                .body("data.findAll { it.gender == 'female' }.size()", lessThanOrEqualTo(
//                        response.jsonPath().getList("data.findAll { it.gender == 'male' }").size())); // Check female vs male count

        JsonPath json = response.jsonPath();
        int femaleCount = json.getList("data.findAll { it.gender == 'female'}").size();
        int maleCount = json.getList("data.findAll { it.gender =='male' }").size();
        System.out.println("Female count: " + femaleCount);
        System.out.println("Male count: " + maleCount);
       // assert femaleCount <= maleCount; // Check female vs male count
        Assert.assertEquals(femaleCount,maleCount);


        //2.yol assertion için

    }
}
