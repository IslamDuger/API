package com.techproed.day05;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetRequest05 {
    /*
    http://dummy.restapiexample.com/api/v1/employees url'ine
    accept type'i "application/json" olan GET request'i yolladigimda
    gelen response'un
    status kodunun 200
    content type'inin "application/json"
    employees sayisinin 24
    employee'lerden birinin "Ashton Cox"
    girilen yaslar icinde 21, 61, ve 23 degerlerinden birinin oldugunu test edin
     */
    @Test
    public void test01(){
        String url="http://dummy.restapiexample.com/api/v1/employees";
        Response response=given().accept("applicati/json").when().get(url);
        response.prettyPrint();
        response.then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("data.id",hasSize(24),
                "data.employee_name",hasItem("Ashton Cox"),
                "data.employee_age",hasItems(21,61,23));
    }
}