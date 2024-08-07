package homework;

import base_urls.SwaggerrBaseUrl;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class HW06 extends SwaggerrBaseUrl {
    /*
      Write an automation test that will create a 'user' using the "https://petstore.swagger.io/" document
    */
    @Test
    public void test01() {
        // Set Url
        spec.pathParams("first", "user");

        // Set Expected Data
        Map<String, Object> payload = new HashMap<>();
        payload.put("id", 100);
        payload.put("username", "JohnDoe"); // Kullanıcı adı boşluk içermemeli
        payload.put("firstName", "John");
        payload.put("lastName", "Doe");
        payload.put("email", "john.doe@example.com");
        payload.put("password", "string"); // Bu alan API'de yok ama eklenmiş
        payload.put("phone", "123-456-7890");
        payload.put("userStatus", 0);

        // Send Request and Get Response
        Response response = given()
                .spec(spec)
                .contentType("application/json")
                .body(payload)
                .when()
                .post("/{first}");
        response.prettyPrint();

        // Do Assertion 1
        response.then().statusCode(200);

        // Yanıtı Map olarak dönüştürme ve değerleri doğrulama
        Map<String, Object> actualData = response.as(Map.class);

        // Beklenen yanıtı oluşturma
        Map<String, Object> expectedResponse = new HashMap<>();
        expectedResponse.put("code", 200);
        expectedResponse.put("type", "unknown");
        expectedResponse.put("message", String.valueOf(payload.get("id"))); // API'nin döndüğü id string tipinde olabilir

        // Yanıtın beklenen verilerle eşleştiğini doğrulama
        Assert.assertEquals(actualData.get("code"), expectedResponse.get("code"));
        Assert.assertEquals(actualData.get("type"), expectedResponse.get("type"));
        Assert.assertEquals(actualData.get("message"), expectedResponse.get("message"));

        // Do Assertion 2 - Yalnızca durum kodu 200 olan isteğin doğrulaması
        response.then()
                .statusCode(200)
                .body("code", equalTo(200)) // response body'sindeki "code"u doğrulama
                .body("type", equalTo("unknown")) // response body'sindeki "type"ı doğrulama
                .body("message", equalTo(String.valueOf(payload.get("id")))); // response body'sindeki "message"ı doğrulama
    }
}
