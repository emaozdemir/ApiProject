package day01;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class C01_SimpleRequestResponse {
/*
    Given
        https://restful-booker.herokuapp.com/booking
    When
        User sends a GET Request to the url
    Then
        HTTP Status Code should be 200
    And
        Content Type should be JSON
    And
        Status Line should be HTTP/1.1 200 OK
    And
        Print Connection and Date headers on console
    And
        Print all headers on console

*/

    public static void main(String[] args) {

        // Basit bir Api sorgusu için RestAssured classından ilgili method çağrılır
        Response response = RestAssured.get("https://restful-booker.herokuapp.com/booking");

        System.out.println("****************** How to print Response **********************");
        System.out.println("response = " + response); // Response yazdırmak için prettyPrint methodu kullanılır.

      // response.prettyPrint();// Response yazdırmak için prettyPrint methodu kullanılır.ama cok uzun diye yoruma aldık

        System.out.println("****************** How to print StatusCode **********************");
        int statusCode = response.statusCode();
        System.out.println("statusCode = " + statusCode);
        //2.yol
        int statusCode2 = response.getStatusCode();
        System.out.println("statusCode2 = " + statusCode2);


        System.out.println("****************** How to print Content Type **********************");
        String contentType = response.contentType();
        System.out.println("contentType = " + contentType);

        System.out.println("****************** How to print Respone Time **********************");
        long responseTime = response.time();
        System.out.println("responseTime = " + responseTime);

        System.out.println("****************** How to print Header Value -Connection & Date **********************");
        String conn = response.header("Connection");
        System.out.println("conn = " + conn);

        String date = response.header("date");
        System.out.println("date = " + date);

        System.out.println("****************** How to print All Header values **********************");
        Headers headers = response.headers();
        System.out.println("headers = " + headers);



    }
}