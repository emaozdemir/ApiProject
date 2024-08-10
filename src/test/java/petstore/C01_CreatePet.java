package petstore;

import base_urls.PetStoreBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class C01_CreatePet extends PetStoreBaseUrl {

    /*
    Given
        https://petstore.swagger.io/v2/pet
    And
        {
              "id": 56793,
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

        //Send the request and get the response
        Response response = given(spec).body(strExpectedData).post("{first}");
        response.prettyPrint();

        //Do assertion
        response.then().statusCode(200).body(
                "id", equalTo(18),
                "category.name", equalTo("Kedi"),
                "name",equalTo("Pamuk"),
                "photoUrls", hasItems("url1", "url2", "url3"),
                "tags.name", hasItems("Beyaz", "Yavru", "Sevimli"),
                "status", equalTo("available"));

    }


}
