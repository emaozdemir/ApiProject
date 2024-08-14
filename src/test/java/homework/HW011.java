package homework;

import base_urls.GorestBaseUrl;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojos.go_rest_posts.RestHomeworkPojo;
import utilities.ObjectMapperUtils;

import static io.restassured.RestAssured.given;

public class HW011 extends GorestBaseUrl {
    /*
    Given
        https://gorest.co.in/public/v1/posts?id=148367
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
                        "current": "https://gorest.co.in/public/v1/posts?page=1",
                        "next": null
                    }
                }
            },
            "data": [
                {
                    "id": 148272,
                    "user_id": 7339723,
                    "title": "Thymum vicissitudo qui adficio tutis speculum textor solium.",
                    "body": "Curatio consuasor vulgo. Dolorem omnis alter. Cicuta comedo nobis. Aduro subnecto umquam. Thermae vox comprehendo. Numquam aeneus cum. Contigo talus iure. Solvo capitulus vestigium. Acquiro crepusculum quas. Animus cibo dignissimos. Crur bos cornu. Vetus ademptio tabesco. Atrocitas terror acsi. Socius velum adeptio. Statim quia tabella. Adduco quia admiratio. Canis vel utroque. Sto sollers cometes."
                }
            ]
        }
    */
    @Test
    public void test01(){
        spec.pathParams("first","public", "second", "v1", "third", "posts")
                .queryParam("id", 148367);

        // Set Expected Data
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
                                "current": "https://gorest.co.in/public/v1/posts?page=1",
                                "next": null
                            }
                        }
                    },
                    "data": [
                        {
                            "id": 148272,
                            "user_id": 7339723,
                            "title": "Thymum vicissitudo qui adficio tutis speculum textor solium.",
                            "body": "Curatio consuasor vulgo. Dolorem omnis alter. Cicuta comedo nobis. Aduro subnecto umquam. Thermae vox comprehendo. Numquam aeneus cum. Contigo talus iure. Solvo capitulus vestigium. Acquiro crepusculum quas. Animus cibo dignissimos. Crur bos cornu. Vetus ademptio tabesco. Atrocitas terror acsi. Socius velum adeptio. Statim quia tabella. Adduco quia admiratio. Canis vel utroque. Sto sollers cometes."
                        }
                    ]
                }""";

        RestHomeworkPojo expectedData = ObjectMapperUtils.convertJsonStrToJava(expectedStr, RestHomeworkPojo.class);
        System.out.println("Expected Data = " + expectedData);

        // Send Request And Get Response
        Response response = given(spec)
                .when()
                .get("/{first}/{second}/{third}");
        response.prettyPrint();

        // Do Assertions
        response
                .then()
                .statusCode(200);

        RestHomeworkPojo actualData = ObjectMapperUtils.convertJsonStrToJava(response.asString(), RestHomeworkPojo.class);
        System.out.println("Actual Data = " + actualData);

        // Meta Assertions
        Assert.assertEquals(actualData.getMeta().getPagination().getTotal(), expectedData.getMeta().getPagination().getTotal());
        Assert.assertEquals(actualData.getMeta().getPagination().getPages(), expectedData.getMeta().getPagination().getPages());
        Assert.assertEquals(actualData.getMeta().getPagination().getPage(), expectedData.getMeta().getPagination().getPage());
        Assert.assertEquals(actualData.getMeta().getPagination().getLimit(), expectedData.getMeta().getPagination().getLimit());
        Assert.assertEquals(actualData.getMeta().getPagination().getLinks().getPrevious(), expectedData.getMeta().getPagination().getLinks().getPrevious());
        Assert.assertEquals(actualData.getMeta().getPagination().getLinks().getCurrent(), expectedData.getMeta().getPagination().getLinks().getCurrent());
        Assert.assertEquals(actualData.getMeta().getPagination().getLinks().getNext(), expectedData.getMeta().getPagination().getLinks().getNext());

        // Data Assertions
        Assert.assertEquals(actualData.getData().get(0).getId(), expectedData.getData().get(0).getId());
        Assert.assertEquals(actualData.getData().get(0).getUserId(), expectedData.getData().get(0).getUserId());
        Assert.assertEquals(actualData.getData().get(0).getTitle(), expectedData.getData().get(0).getTitle());
        Assert.assertEquals(actualData.getData().get(0).getBody(), expectedData.getData().get(0).getBody());
    }
}
