package homework;

import base_urls.ReqresBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class HW04 extends ReqresBaseUrl {
    /*
     Given
            https://reqres.in/api/unknown/
     When
          I send GET Request to the URL
     Then
          1)Status code is 200
          2)Print all pantone_values
          3)Print all ids greater than 3 on the console
            Assert that there are 3 ids greater than 3
          4)Print all names whose ids are less than 3 on the console
            Assert that the number of names whose ids are less than 3 is 2
     */
    @Test
    public void test01() {
        // Set Url
        spec.pathParam("first", "unknown");

        // Set Expected Data

        // Send Request and Get Response
        Response response = given().spec(spec).when().get("{first}");
        response.prettyPrint();

        // 1) Status code is 200
        assertEquals(response.statusCode(), 200);

        JsonPath json = response.jsonPath(); // Create JsonPath object from the response

        // 2) Print all pantone_values
        List<String> pantone_valuesList=json.getList("data.pantone_value");
        System.out.println("pantone_valuesList = " + pantone_valuesList);


        // 3) Print all ids greater than 3 on the console and assert that there are 3 ids greater than 3
        List<String> idsGreaterThan3=json.getList("data.findAll{it.id>3}");
        //System.out.println("idsGreaterThan3 = " + idsGreaterThan3);
        assertEquals(idsGreaterThan3.size(), 3);
        System.out.println("idsGreaterThan3.size() = " + idsGreaterThan3.size());

        // 4) Print all names whose ids are less than 3 on the console and assert that there are 2 names whose ids are less than 3
        List<String> namesWhoseIdsLessThan3 = json.getList("data.findAll { it.id < 3 }.name");
        System.out.println("names whose ids less than 3 = " + namesWhoseIdsLessThan3);
        Assert.assertEquals(namesWhoseIdsLessThan3.size(), 2);

    }
}
