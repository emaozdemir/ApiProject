package contactlist;

import base_urls.ContactListBaseUrl;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojos.go_contact_list_doc.ContactListPojo;
import utilities.ObjectMapperUtils;

import static io.restassured.RestAssured.given;

public class C01_CreateContact extends ContactListBaseUrl {

    @Test
    public void createContact() {
        spec.pathParams("first", "contacts");

        // set the data
        String payload = """
                {
                    "firstName": "John",
                    "lastName": "Doe",
                    "birthdate": "1970-01-01",
                    "email": "jdoe@fake.com",
                    "phone": "8005555555",
                    "street1": "1 Main St.",
                    "street2": "Apartment A",
                    "city": "Anytown",
                    "stateProvince": "KS",
                    "postalCode": "12345",
                    "country": "USA"
                }""";
        ContactListPojo contactListPojo = ObjectMapperUtils.convertJsonStrToJava(payload, ContactListPojo.class);
        // send request and get response
        Response response = given(spec).when().body(payload).post("{first}");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 201);

    }
}
