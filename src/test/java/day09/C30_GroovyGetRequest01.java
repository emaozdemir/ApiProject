package day09;

import base_urls.GorestBaseUrl;
import io.restassured.http.ContentType;
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
                Vasudev Mehra, Kanishka Banerjee, Girindra Pilla, Purnima Joshi DVM are among the users -> This may change
            And
                The female users are less than/ equal to male users -> This may change
    */

    @Test
    public void test(){
        // Set Url
        spec.pathParam("first","users");

        // Set Expected Data

        // Sen Request And Get Response
        Response response =given(spec)
                .when()
                .get("{first}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("meta.pagination.limit",equalTo(10))
                .body("meta.pagination.links.current",equalTo("https://gorest.co.in/public/v1/users?page=1"))
                .body("data.id",hasSize(10))
                .body("data.status",hasItem("active"))
                .body("data.name", hasItems("Rev. Mahesh Bhat", "Chaturaanan Guha DC", "Somnath Bhattathiri"))
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        int femaleUsers = json.getList("data.findAll{it.gender=='female'}").size();
        int maleUsers = json.getList("data.findAll{it.gender=='male'}").size();

        System.out.println(femaleUsers);
        System.out.println(maleUsers);

       // Assert.assertEquals(femaleUsers,maleUsers);
        assert femaleUsers<=maleUsers;

        //2.YOL ASSERTİON İÇİN
        int paginationLimit = json.getInt("meta.pagination.limit");
        Assert.assertEquals(paginationLimit,10);

    }
}