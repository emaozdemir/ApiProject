package utilities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthenticationBooker {

    public static String generateToken(){
        String url = "https://restful-booker.herokuapp.com/auth";
        String credentials = """
                {
                    "username" : "admin",
                    "password" : "password123"
                }""";
        Response response = given()
                                 .body(credentials)
                                 .contentType(ContentType.JSON)
                                 .post(url);
        response.prettyPrint();
        return response.jsonPath().getString("token");
    }
}
