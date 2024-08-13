package homework;

import base_urls.ReqresBaseUrl;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojos.PetPojo;
import pojos.ReqresPojo;
import utilities.ObjectMapperUtils;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class HW09 extends ReqresBaseUrl {
    //  Aşağıdaki görevi pojo kullanarak yapınız
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
    public void test02() {
//        1. Set the URL //spec ReqresBaseUrl den inherit edilmiş durumda hep
        spec.pathParam("first", "users");
//        2. Set the expected data  //  Beklenen Veriyi Ayarla
        String strExpectedData= """
                        {
                        "name": "morpheus",
                        "job": "leader"
                        }
                        """;

        ReqresPojo expectedData = ObjectMapperUtils.convertJsonStrToJava(strExpectedData, ReqresPojo.class);// JSON string olarak aldıgımız datayı Java pojoya cevirdik
        System.out.println("expectedData = " + expectedData);//default const. bu donusturme işleminde kullanır

//        3. Send the request and get the response
        Response response = given(spec).body(expectedData).when().post("{first}");//expectedData olusan pojoyu post req olarak gonderdik
//        response.prettyPrint();
//        4. Do Assertion
        ReqresPojo actualData = ObjectMapperUtils.convertJsonStrToJava(response.asString(),ReqresPojo.class);//gelen Response body'yi pojoya cevirelim
        System.out.println("actualData = " + actualData);
        Assert.assertEquals(response.statusCode(), 201);
        Assert.assertEquals(actualData.getName(), expectedData.getName());
        Assert.assertEquals(actualData.getJob(), expectedData.getJob());


    }
//    @Test
//    public void test01() {
////        1. Set the URL //spec ReqresBaseUrl den inherit edilmiş durumda hep
//        spec.pathParam("first", "users");
////        2. Set the expected data  //  Beklenen Veriyi Ayarla
//        ReqresPojo payload = new ReqresPojo("morpheus", "leader");
//
////        3. Send the request and get the response
//        Response response = given(spec).body(payload).when().post("{first}");
//        response.prettyPrint();
//
////        4. Do Assertion
//        //1.yol Assertion (Hamcrest Matchers)
//        response
//                .then()
//                .statusCode(201)
//                .body("name", equalTo(payload.getName()))
//                .body("job", equalTo(payload.getJob()));
//
//        //2.yol Assertion (responsu Map olarak dönüştürebiliriz)
//        Map<String, Object> actualData = response.as(Map.class); // De-Serialization : Json Objesini Java Objesine çevirme işlemine denir
//        Assert.assertEquals(actualData.get("name"), payload.getName());
//        Assert.assertEquals(actualData.get("job"), payload.getJob());
//    }
}
