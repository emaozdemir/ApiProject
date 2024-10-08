package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;

public class AutomationExerciseBaseUrl {

    protected RequestSpecification spec;  // nulldir. Her methoddan önce spec objesine değer atamak istiyorum

    @BeforeMethod
    public void setUp() {
        spec = new RequestSpecBuilder()
                .setBaseUri("https://automationexercise.com/api")
                .setContentType(ContentType.JSON)
                .build();
    }


}
