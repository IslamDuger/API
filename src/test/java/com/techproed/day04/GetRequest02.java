package com.techproed.day04;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;


public class GetRequest02 {
         /*
Bir GET Request asagida verilen Endpoint'e yollanir
       https://restful-booker.herokuapp.com/booking
     status code 200'dur
a content type  "application/json" dir.


   */

    @Test
    public void test01(){
        String url="https://restful-booker.herokuapp.com/booking";
        Response response=given().
                          accept("aplication/json").
                          when().
                          get(url);
        response.prettyPrint();
        response.then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON);
        System.out.println(response.getHeaders());
        System.out.println("==============================================");
        System.out.println(response.getHeader("server"));
        System.out.println(response.getHeader("Content-Type"));
    }
    @Test
    public void test02(){
        /*

       https://restful-booker.herokuapp.com/booking/1001 url'ine
accept type'i "application/json" olan GET request'i yolladigimda
gelen response'un
status kodunun 404 oldugunu
ve Response body'sinin "Not Found" icerdigini
ve Response body'sinin "API" icermedigini test edin
     */
        String url="https://restful-booker.herokuapp.com/booking/1001";
        Response response=given().accept("aplication/json").when().get(url);
        response.prettyPrint();
        response.then().assertThat().statusCode(404);

        Assert.assertTrue(response.asString().contains("Not Found"));
        Assert.assertFalse(response.asString().contains("API"));


    }
}
