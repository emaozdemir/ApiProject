package day04;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class C10_GroovyLanguage extends JsonPlaceHolderBaseUrl {
                /*
      Given
             https://jsonplaceholder.typicode.com/todos
      When
           I send GET Request to the URL
      Then
           1)Status code is 200
           2)Print all ids greater than 190 on the console
             Assert that there are 10 ids greater than 190
           3)Print all userIds whose ids are less than 5 on the console
             Assert that the number of userIds whose ids are less than 5 is 4
           4)Print all titles whose ids are less than 5
             Assert that "delectus aut autem" is one of the titles whose id is less than 5
   */

    @Test
    public void groovyTest(){
        // Set Url
        spec.pathParam("first","todos");

        // Set Expected Data

        // Sent Request and Get Response
        Response response = given(spec).when().get("{first}");
        //response.prettyPrint();

        // Do Assertions
        JsonPath json = response.jsonPath();
        // System.out.println(json.getString("[3].title")); // Herhangi bir datayı Jsonpath ile çekebilirim

        // 1)Status code is 200
        Assert.assertEquals(response.statusCode(),200);


        //  2)Print all ids greater than 190 on the console
        //    Assert that there are 10 ids greater than 190

        // 1. Way:
        List<Integer> idList = json.getList("id");
        //System.out.println("idList = " + idList);

        System.out.println();

        int idsGreaterThan190 = 0;
        for (Integer id: idList){
            if (id>190){
                idsGreaterThan190++;
                System.out.print(id + " ");
            }
        }
        System.out.println("idsGreaterThan190 = " + idsGreaterThan190);
        Assert.assertEquals(idsGreaterThan190,10);

        // 2.Yol: elimizde liste varsa filtrelemekte kullanırız
        List<Integer> idListGroovy = json.getList("findAll{it.id > 190}");
        Assert.assertEquals(idListGroovy.size(),10);
        System.out.println("idListGroovy = " + idListGroovy);


        //  3)Print all userIds whose ids are less than 5 on the console
        //  Assert that the number of userIds whose ids are less than 5 is 4

        List<Integer> userIdsListIdLessThan5 = json.getList("findAll{it.id<5}.userId");
        System.out.println("userIdsListIdLessThan5 = " + userIdsListIdLessThan5);
        Assert.assertEquals(userIdsListIdLessThan5.size(),4);

        //  4)Print all titles whose ids are less than 5
        //  Assert that "delectus aut autem" is one of the titles whose id is less than 5

        List<String> titlesLessThan5 = json.getList("findAll{it.id<5}.title");
        System.out.println("titlesLessThan5 = " + titlesLessThan5);
        Assert.assertTrue(titlesLessThan5.contains("delectus aut autem"));

        List<Integer> id = json.getList("findAll{it.title='delectus aut autem'}.id");
        Integer idOfTitle = id.get(0);
        System.out.println("idOfTitle = " + idOfTitle);

        int idOfTitle2 = (int)json.getList("findAll{it.title='delectus aut autem'}.id").getFirst();

    }
}