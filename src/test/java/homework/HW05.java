package homework;

import base_urls.ReqresBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

public class HW05 extends ReqresBaseUrl {
     /*
        Given
            1) https://reqres.in/api/users
            2) {
                "name": "morpheus",
                "job": "leader"
                }
        When
            I send POST Request to the Url
        Then
            Status code is 201
            And response body should be like
            {
            "name": "morpheus",
            "job": "leader",
            "id": "496",
            "createdAt": "2022-10-04T15:18:56.372Z"
            }
     */
     @Test
     public void test01() {
         // Set Url  //spec ReqresBaseUrl den inherit edilmiş durumda hep
         spec.pathParam("first", "users");

         // Set Expected Data  Beklenen Veriyi Ayarla
         Map<String, Object> payload = new HashMap<>();
         payload.put("name","morpheus");
         payload.put("job","leader");

         // Send Request get Response İsteği Gönder ve Yanıtı Al
         Response response = given(spec)
                 .body(payload)  // Serialization --> Java Objesini Json/XML formatına dönüştürme işlemine denir/(gidip pom.xmle dep ekledik)
                 .when()        // Serialization için "Please put Jackson (Databind), Gson, Johnzon, or Yasson in the classpath"
                 .post("{first}");
         response.prettyPrint();

         // Do Assertion
         response
                 .then()
                 .statusCode(201) // Durum Kodunu Doğrula
                 .body("name",equalTo(payload.get("name")))// Yanıt bodysini Doğrula//De-Serialization : JSON formatını Java Objesine çevirme işlemine denir
                 .body("job",equalTo(payload.get("job")));


         // Alternatif olarak responsu Map olarak dönüştürebiliriz ve değerleri doğrulayabiliriz
         Map<String,Object> actualData = response.as(Map.class); // De-Serialization : Json/XML formatını Java Objesine çevirme işlemine denir
//         üst satırda, response objesi (REST API'den gelen yanıt) Map sınıfına dönüştürülüyor.
//         response.as(Map.class) ifadesi, yanıtı Map<String, Object> türüne dönüştürür.
         Assert.assertEquals(actualData.get("name"),payload.get("name"));
         Assert.assertEquals(actualData.get("job"),payload.get("job"));
     }
}