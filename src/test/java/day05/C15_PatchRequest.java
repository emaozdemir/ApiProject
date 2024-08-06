package day05;

import base_urls.JsonPlaceHolderBaseUrl;
import org.testng.annotations.Test;

public class C15_PatchRequest extends JsonPlaceHolderBaseUrl {
    /*
        Given
            1) https://jsonplaceholder.typicode.com/todos/198
            2) {
                 "title": "Read Books"
               }
        When
            I send PATCH Request to the Url
        Then
           Status code is 200
           And response body is like  {
                                            "userId": 10,
                                            "id": 198,
                                            "title": "Read Books",
                                            "completed": true
                                        }
    */

    @Test
    public void patchRequestTest(){
       //Set the url
        spec.pathParams("first", "todos", "second", "198");

        //Set the expected data





    }
}
