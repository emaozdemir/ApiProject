package day03;

import base_urls.RestFullBookerBaseUrl;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class C07_QueryParametreleri extends RestFullBookerBaseUrl {

    //============== Query FİLTRELEME==========
          /*
       Given
           https://restful-booker.herokuapp.com/booking
       When
           User sends get request to the URL
       Then
           Status code is 200
         And
           Among the data there should be someone whose firstname is "John" and lastname is "Smith"

           linklerdeki herbir & bu sembolden sonra gelen yazı vs  query parametresi demek
           (https://www.google.com/search?q=herganfi&oq=herganfi&gs_lcrp=EgZjaHJvbWUyBggAEEUYOTIJCAEQABgNGIAE
           MgkIAhAAGA0YgAQyCQgDEAAYDRiABDIJCAQQABgNGIAEMgkIBRAAGA0YgAQyCQgGEAAYDRiABDIJCAcQABgNGIAEMgkICBAAGA
           0YgAQyCQgJEC4YDRiABNIBCDI0MzlqMGo3qAIIsAIB&sourceid=chrome&ie=UTF-8&zx=1722803569582&no_sw_cr=1)
    */

    @Test
    public void test01(){
        //    1. Set the URL
        spec.pathParam("first","booking")
                .queryParams("firstname","John","lastname","Smith");
        //    2. Set the expected data
        //    3. Send the request and get the response
        Response response = given(spec).when().get("{first}");
      //  response.prettyPrint();
        //    4. Do Assertion

        response
                .then()
                .statusCode(200)
                .body(containsString("bookingid"))
                .body("",hasSize(greaterThan(0)));

        String responseStr = response.asString();
        Assert.assertTrue(responseStr.contains("bookingid"));
    }
}