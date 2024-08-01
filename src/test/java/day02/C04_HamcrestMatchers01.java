package day02;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class C04_HamcrestMatchers01 {
    /*
    Given
        https://jsonplaceholder.typicode.com/todos/23
    When
        User sends a GET request to the URL
    Then
        HTTP Status Code should be 200
    And
        Response format should be “application/json”
    And
        “title” is “et itaque necessitatibus maxime molestiae qui quas velit”
    And
        “completed” is false
    And
        “userId” is 2
*/

    @Test
    public void test01(){
        //    1. Set the URL
        String url = "https://jsonplaceholder.typicode.com/todos/23";
        //    2. Set the expected data
        //    3. Send the request and get the response
        Response response = given().when().get(url);
        response.prettyPrint();
        //    4. Do Assertion

        // Hard Assertion mantığı ile assertion yapacaksak ayrı body() methodlarında assertionlar yazılır
        response.
                then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("title",equalTo("et itaque necessitatibus maxime molestiae qui quas velit"))
                .body("title",is("et itaque necessitatibus maxime molestiae qui quas velit"))
                .body("completed",equalTo(false))
                .body("userId",equalTo(2));

        // Soft Assertion mantığı ile assertion yapacaksak n body() methodu içerisinde assertionlar yazılır
        response.
                then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("title",equalTo("et itaque necessitatibus maxime molestiae qui quas velit"),
                        "completed",equalTo(false),
                        "userId",equalTo(2)
                );
        // equalto() : path tanımlanan verinin değerini assert ediyor
        // containsString() : bodynin string değeri içerip içermediğini assert eder














    }

}
