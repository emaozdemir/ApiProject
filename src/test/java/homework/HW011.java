package homework;

import base_urls.GorestBaseUrl;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojos.go_rest_posts.RestHomeworkPojo;
import utilities.ObjectMapperUtils;

import static io.restassured.RestAssured.given;

public class HW011 extends GorestBaseUrl {

    @Test
    public void test01() {
        // Endpoint ve query parametreleri düzenleme
        spec.pathParams("first", "posts")
                .queryParam("id", 148367);

        // set the data
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
                            "id": 148367,
                            "user_id": 7342384,
                            "title": "Cursim tripudio dolorem aufero assumenda templum voveo et cunae.",
            "body": "Vindico theca acidus. Nisi audax stillicidium. Repellendus tantillus compono. Viscus quia adinventitias. Tabgo magnam vester. Praesentium blandior addo. Coadunatio comptus turbo. Atavus nihil desidero. Cerno triginta decipio. Modi velociter a. Animi suspendo umerus."                        }
                    ]
                }""";

        RestHomeworkPojo expectedData = ObjectMapperUtils.convertJsonStrToJava(expectedStr, RestHomeworkPojo.class);
        //ObjectMapperUtils.convertJsonStrToJava: Bu method, String formatındaki JSON verisini Java nesnesine (POJO) dönüştürüyor.
        System.out.println("Expected Data = " + expectedData);

        // send request and get response
        Response response = given(spec)
                .when()
                .get("/{first}");
        response.prettyPrint();

        // do assertions
        response.then().statusCode(200);

        RestHomeworkPojo actualData = ObjectMapperUtils.convertJsonStrToJava(response.asString(), RestHomeworkPojo.class);
        System.out.println("Actual Data = " + actualData);

        // Meta assertion
        Assert.assertEquals(actualData.getMeta().getPagination().getTotal(), expectedData.getMeta().getPagination().getTotal());
        Assert.assertEquals(actualData.getMeta().getPagination().getPages(), expectedData.getMeta().getPagination().getPages());
        Assert.assertEquals(actualData.getMeta().getPagination().getPage(), expectedData.getMeta().getPagination().getPage());
        Assert.assertEquals(actualData.getMeta().getPagination().getLimit(), expectedData.getMeta().getPagination().getLimit());
        Assert.assertEquals(actualData.getMeta().getPagination().getLinks().getPrevious(), expectedData.getMeta().getPagination().getLinks().getPrevious());
        Assert.assertEquals(actualData.getMeta().getPagination().getLinks().getCurrent(), expectedData.getMeta().getPagination().getLinks().getCurrent());
        Assert.assertEquals(actualData.getMeta().getPagination().getLinks().getNext(), expectedData.getMeta().getPagination().getLinks().getNext());

        // Data assertion
        Assert.assertEquals(actualData.getData().get(0).getId(), expectedData.getData().get(0).getId());
        Assert.assertEquals(actualData.getData().get(0).getUserId(), expectedData.getData().get(0).getUserId());
        Assert.assertEquals(actualData.getData().get(0).getTitle(), expectedData.getData().get(0).getTitle());
        Assert.assertEquals(actualData.getData().get(0).getBody(), expectedData.getData().get(0).getBody());
    }
}
