package day07;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojos.JsonPlaceHolderPojo;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class C23_PojoList extends JsonPlaceHolderBaseUrl {
    /*
    map bir interfacetir
       Given
           https://jsonplaceholder.typicode.com/todos
       When
            I send a GET request to the Url
       Then
           HTTP Status Code should be 200
       And
           First todo must be like:
                   {
                           "userId": 1,
                           "id": 1,
                           "title": "delectus aut autem",
                           "completed": false
                   }
*/
    @Test
    public void test01(){
        // Set Url
        spec.pathParam("first","todos");

        // Set Expected Data
        JsonPlaceHolderPojo expectedData =
                new JsonPlaceHolderPojo(1,
                        "delectus aut autem",
                        false);

        // Send Request And Get Response
        Response response = given(spec).when().get("{first}");
        response.prettyPrint();

        // Do Assertions
        response
                .then()
                .statusCode(200)
                .body("[0].userId",equalTo(expectedData.getUserId()))
                .body("[0].title",equalTo(expectedData.getTitle()))
                .body("[0].completed",equalTo(expectedData.getCompleted()));
        // 2. Yöntem:
        //düzhün bir yontem değil tercih edilmez
        //List<JsonPlaceHolderPojo> actualData = response.as(List.class);//List.class obje listesine cevirir sonrada JsonPlaceHolderPojo ya cevirir
        //System.out.println("actualData = " + actualData);//type castingler vs olur kayıplar olur hatalar cakısmalar olur
        /*
            Bu kod, JSON verilerini sadece bir List olarak döndürecektir.
        Ancak bu listenin içindeki nesnelerin türü Object olacaktır ve bu
        nesneleri JsonPlaceHolderPojo türüne dönüştürmek için ayrıca bir
        işlem yapmanız gerekecektir.
            response.as(List.class) kullanmak yerine
         response.as(new TypeRef<List<JsonPlaceHolderPojo>>() {}) kullanmalıyız.
         Bu, JSON verilerinin doğru türdeki nesnelere dönüştürülmesini garanti
         eder ve tip dönüşümleri sırasında oluşabilecek hataları önler.
         */
        List<JsonPlaceHolderPojo> actualDataList1 = response.as(new TypeRef<List<JsonPlaceHolderPojo>>(){});
        List<JsonPlaceHolderPojo> actualDataList = response.as(new TypeRef<>(){}); // Yeni sürümlerde datanın yukarıdaki gibi yazılmasına gerek kalmadı
        System.out.println("actualData = " + actualDataList);

        /*
        Map<String,Object> actualMap = response.as(Map.class);
        Map<String,Object> actualMap2 = response.as(new TypeRef<>() {});//garantici olmak istersek donusumleri boyle yaparız

         */

        JsonPlaceHolderPojo actualData = actualDataList.getFirst();

        Assert.assertEquals(actualData.getUserId(),expectedData.getUserId());
        Assert.assertEquals(actualData.getTitle(),expectedData.getTitle());
        Assert.assertEquals(actualData.getCompleted(),expectedData.getCompleted());
    }

}