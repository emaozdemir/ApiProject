package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;

public class SpaceXDataBaseUrl {

    protected RequestSpecification spec;  //

    @BeforeMethod
    public void setUp(){
        spec = new RequestSpecBuilder()
                .setBaseUri("https://api.spacexdata.com/v3")
                .setContentType(ContentType.JSON)
                .build();
    }

}
