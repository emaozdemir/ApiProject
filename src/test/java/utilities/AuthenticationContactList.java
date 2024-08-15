package utilities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthenticationContactList {

    public static String generateToken() {

        String url = "https://thinking-tester-contact-list.herokuapp.com/users/login";
        String credentials = """
                {
                           "email": "tester@test12.com",
                           "password": "Tester.12"
                }""";
        Response response = given()
                .body(credentials)
                .contentType(ContentType.JSON)
                .post(url);
        response.prettyPrint();
        return response.jsonPath().getString("token");
    }
}
