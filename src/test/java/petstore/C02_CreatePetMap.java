package petstore;

import base_urls.PetStoreBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utilities.ObjectMapperUtils;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.testng.Assert.assertEquals;

public class C02_CreatePetMap extends PetStoreBaseUrl {

    /*
    Given
        https://petstore.swagger.io/v2/pet
    And
        {
              "id": 18,
              "category": {
                "id": 0,
                "name": "Kedi"
              },
              "name": "Pamuk",
              "photoUrls": [
                "url1", "url2", "url3"
              ],
              "tags": [
                {
                  "id": 0,
                  "name": "Beyaz"
                },
                {
                  "id": 1,
                  "name": "Yavru"
                },
                {
                  "id": 2,
                  "name": "Sevimli"
                }

              ],
              "status": "available"
            }

       When
            User send POST request
       Then
            Status code should be 200
       And
            Response body should be like:
             {
              "id": 18,
              "category": {
                "id": 0,
                "name": "Kedi"
              },
              "name": "Pamuk",
              "photoUrls": [
                "url1", "url2", "url3"
              ],
              "tags": [
                {
                  "id": 0,
                  "name": "Beyaz"
                },
                {
                  "id": 1,
                  "name": "Yavru"
                },
                {
                  "id": 2,
                  "name": "Sevimli"
                }

              ],
              "status": "available"
            }
     */

    @Test
    void createPetTest() {
        //Set the url
        spec.pathParams("first", "pet");

        //Set the expected data
        String strExpectedData = """
                        {
                              "id": 18,
                              "category": {
                                "id": 0,
                                "name": "Kedi"
                              },
                              "name": "Pamuk",
                              "photoUrls": [
                                "url1", "url2", "url3"
                              ],
                              "tags": [
                                {
                                  "id": 0,
                                  "name": "Beyaz"
                                },
                                {
                                  "id": 1,
                                  "name": "Yavru"
                                },
                                {
                                  "id": 2,
                                  "name": "Sevimli"
                                }

                              ],
                              "status": "available"
                            }
                """;

        Map expectedData = ObjectMapperUtils.convertJsonStrToJava(strExpectedData, Map.class);
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given(spec).body(expectedData).post("{first}");
        response.prettyPrint();

        //Do assertion
        Map actualData = ObjectMapperUtils.convertJsonStrToJava(response.asString(), Map.class);//responsu mapa ceviriyoruz
        System.out.println("actualData = " + actualData);

        assertEquals(response.statusCode(), 200);
        assertEquals(actualData.get("id"), expectedData.get("id"));
        assertEquals(((Map) actualData.get("category")).get("name"), ((Map) expectedData.get("category")).get("name"));
        assertEquals(actualData.get("name"), expectedData.get("name"));
        assertEquals(((List) actualData.get("photoUrls")), ((List) expectedData.get("photoUrls")));
        assertEquals(((Map) ((List) actualData.get("tags")).get(0)).get("name"), ((Map) ((List) expectedData.get("tags")).get(0)).get("name"));
        assertEquals(((Map) ((List) actualData.get("tags")).get(1)).get("name"), ((Map) ((List) expectedData.get("tags")).get(1)).get("name"));
        assertEquals(((Map) ((List) actualData.get("tags")).get(2)).get("name"), ((Map) ((List) expectedData.get("tags")).get(2)).get("name"));
        assertEquals(actualData.get("status"), expectedData.get("status"));



    }


}