package com.techproed.day05;

import com.techproed.testBase.TestBaseRestfulBooker;
import com.techproed.testBase.TestBaseRestfulBooker;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest07 extends TestBaseRestfulBooker {


    @Test
    public void test01(){
        /*
        https://restful-booker.herokuapp.com/booking/5 url'ine bir request yolladigimda
     	HTTP Status Code'unun 200
     	response content type'inin "application/JSON" oldugunu
		response body'sinin asagidaki gibi oldugunu test edin
				{"firstname": Sally,
     			"lastname": "Smith",
     			"totalprice": 789,
     			"depositpaid": false,
     			"bookingdates": { 	"checkin": "2017-12-11",
     	                     						"checkout":"2020-02-20" }

         */
      spec01.pathParams("name","booking","id",5);
        Response response=given().accept("application/json").spec(spec01).when().get("/{name}/{id}");

    }
}
