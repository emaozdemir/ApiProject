package day09;

import base_urls.GorestBaseUrl;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojos.go_rest_users.DataItem;
import pojos.go_rest_users.GoRestPojo;
import utilities.ObjectMapperUtils;

import static io.restassured.RestAssured.given;

public class C32_PojoGeneratorGetRequest extends GorestBaseUrl {
    /*
Given
    https://gorest.co.in/public/v1/users?id=7342369
When
    User sends GET request
Then
    HTTP Status Code should be 200
And
    Response body should be like:
        {
            "meta": {
                "pagination": {
                    "total": 1,
                    "pages": 1,
                    "page": 1,
                    "limit": 10,
                    "links": {
                        "previous": null,
                        "current": "https://gorest.co.in/public/v1/users?page=1",
                        "next": null
                    }
                }
            },
            "data": [
                {
            "id": 7342369,
            "name": "Mahendra Varrier",
            "email": "varrier_mahendra@hansen.test",
            "gender": "female",
            "status": "inactive"
        }

            ]
        }
 */

    @Test
    public void test() {
        // Set Url
        spec.pathParam("first", "users")
                .queryParam("id", 7342369);
        // Set ExpectedData
        // DataItem dataItem=new DataItem("female","Mahendra Varrier",....); bunun için parametreli const ihtiyacımız olurdu.

        // Set ExpectedData
        String expectedStr = """
                {
                            "meta": {
                                "pagination": {
                                    "total": 1,
                                    "pages": 1,
                                    "page": 1,
                                    "limit": 10,
                                    "links": {
                                        "previous": null,
                                        "current": "https://gorest.co.in/public/v1/users?page=1",
                                        "next": null
                                    }
                                }
                            },
                            "data": [
                                 {
                            "id": 7339729,
                            "name": "Shakuntala Pilla",
                            "email": "pilla_shakuntala@kihn.example",
                            "gender": "female",
                            "status": "inactive"
                        }
                                
                            ]
                        }""";
        GoRestPojo expectedData = ObjectMapperUtils.convertJsonStrToJava(expectedStr, GoRestPojo.class);
        //System.out.println("expectedData = " + expectedData);

        // Sent Request And Get Response
        Response response = given(spec)
                .when()
                .get("{first}");
        response.prettyPrint();
        // Do Assertions:
        response
                .then()
                .statusCode(200);

        GoRestPojo actualData = ObjectMapperUtils.convertJsonStrToJava(response.asString(), GoRestPojo.class);

        Assert.assertEquals(actualData.getMeta().getPagination().getTotal(), expectedData.getMeta().getPagination().getTotal());
        Assert.assertEquals(actualData.getMeta().getPagination().getPages(), expectedData.getMeta().getPagination().getPages());
        Assert.assertEquals(actualData.getMeta().getPagination().getPage(), expectedData.getMeta().getPagination().getPage());
        Assert.assertEquals(actualData.getMeta().getPagination().getLimit(), expectedData.getMeta().getPagination().getLimit());

        Assert.assertEquals(actualData.getMeta().getPagination().getLinks().getPrevious(), expectedData.getMeta().getPagination().getLinks().getPrevious());
        Assert.assertEquals(actualData.getMeta().getPagination().getLinks().getCurrent(), expectedData.getMeta().getPagination().getLinks().getCurrent());
        Assert.assertEquals(actualData.getMeta().getPagination().getLinks().getNext(), expectedData.getMeta().getPagination().getLinks().getNext());

        Assert.assertEquals(actualData.getData().getFirst().getId(), expectedData.getData().getFirst().getId());
        Assert.assertEquals(actualData.getData().getFirst().getName(), expectedData.getData().getFirst().getName());
        Assert.assertEquals(actualData.getData().getFirst().getEmail(), expectedData.getData().getFirst().getEmail());
        Assert.assertEquals(actualData.getData().getFirst().getGender(), expectedData.getData().getFirst().getGender());
        Assert.assertEquals(actualData.getData().getFirst().getStatus(), expectedData.getData().getFirst().getStatus());
    }
}
