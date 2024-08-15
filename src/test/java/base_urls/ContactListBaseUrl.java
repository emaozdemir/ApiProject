package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;

import static utilities.AuthenticationContactList.generateToken;

public class ContactListBaseUrl {

    protected RequestSpecification spec;  // Şu an spec null dır. Her methoddan önce spec objeme değer atamak (kurmak) istiyorum

    public static String token;
    @BeforeMethod
    public void setUp(){
        if (token == null){
            token = generateToken();
        }
        System.out.println("token = " + token);
        spec = new RequestSpecBuilder()
                .setBaseUri("https://thinking-tester-contact-list.herokuapp.com")
                .addHeader("Authorization","Bearer "+ token)
                .setContentType(ContentType.JSON)
                .build();
    }
}
