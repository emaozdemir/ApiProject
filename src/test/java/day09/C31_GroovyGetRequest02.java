package day09;

import base_urls.DummyrestBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class C31_GroovyGetRequest02 extends DummyrestBaseUrl {
    /*
    Given
        https://dummy.restapiexample.com/api/v1/employees
    When
        User sends Get Request to the Url
    Then
        Status code is 200
    And
        There are 24 employees
    And
        "Tiger Nixon" and "Garrett Winters" are among the employees
    And
        The greatest age is 66
    And
        The name of the lowest age is "Tatyana Fitzpatrick"
    And
        Total salary of all employees is 6,644,770
 */
    @Test
    public void test(){
        // Set Url
        spec.pathParam("first","employees");

        // Send Request Get Response
        Response response = given(spec)
                                      .when()
                                      .get("{first}")
                                      .then()
                                      .statusCode(200)
                                      .extract()
                                      .response();

        // Do Assertions

        JsonPath json = response.jsonPath();

        // There are 24 employees
        List<String> employeeNameList = json.getList("data.employee_name");
        System.out.println("employeeNameList = " + employeeNameList);

        Assert.assertEquals(employeeNameList.size(),24);

        //"Tiger Nixon" and "Garrett Winters" are among the employees
        Assert.assertTrue(employeeNameList.contains("Tiger Nixon") && employeeNameList.contains("Garrett Winters"));

        //The greatest age is 66
        List<Integer> employeeAgeList = json.getList("data.employee_age");
        System.out.println("employeeAgeList = " + employeeAgeList);

        int maxAge =0;
        for (Integer age: employeeAgeList){
            if (age>maxAge){
                maxAge=age;
            }
        }

        Assert.assertEquals(maxAge,66);

    //The name of the lowest age is "Tatyana Fitzpatrick"
        int lowestAge =200;

        for (Integer age: employeeAgeList){
            if (age<lowestAge){
                lowestAge=age;
            }

        }
        System.out.println("lowestAge = " + lowestAge);

        List <String> youngEmpList = json.getList("data.findAll{it.employee_age=="+ lowestAge +"}.employee_name");
        String youngestEmployee = youngEmpList.getFirst();
        Assert.assertEquals(youngestEmployee,"Tatyana Fitzpatrick");

        //Total salary of all employees is 6,644,770
        List<Integer> employeeSalaryList = json.getList("data.employee_salary");

        Integer sumOfSalaries = 0;
        for (Integer salary:employeeSalaryList){
            sumOfSalaries += salary;
           // sumOfSalaries = sumOfSalaries + salary;
        }
        Assert.assertEquals(sumOfSalaries,6644770);


    }
}
