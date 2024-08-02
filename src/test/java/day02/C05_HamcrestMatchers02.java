package day02;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class C05_HamcrestMatchers02 {
        /*
    Given
        https://jsonplaceholder.typicode.com/todos
    When
        I send a GET request to the Url
    Then
        HTTP Status Code should be 200
    And
        Response format should be "application/json"
    And
        There should be 200 todos
    And
        "quis eius est sint explicabo" should be one of the todos title
    And
        2, 7, and 9 should be among the userIds
*/

    @Test
    public void test01(){
        //     1. Set the URL
        String url = "https://jsonplaceholder.typicode.com/todos";
        //    2. Set the expected data
        //    3. Send the request and get the response
        Response response = given().when().get(url);
       // response.prettyPrint();
        //    4. Do Assertion
        response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                // .body("[0].title",equalTo("delectus aut autem"));
                .body("id",hasSize(200))
                .body("title",hasItem("quis eius est sint explicabo"))
                .body("userId",hasItems(2,7,9));

        // hasSize() : listenin size ını assert eder
        // hasItem() : listede bir elemanın bulunup bulunmadığını kontrol eder (contains gibi...)
        // hasItems() : listede çoklu eleman sorgularında kullanılır. (containsAll() gibi...)
    }

}