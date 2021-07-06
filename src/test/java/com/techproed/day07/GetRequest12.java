package com.techproed.day07;

import com.techproed.testBase.TestBaseRestfulBooker;
import com.techproed.testData.TestDataHerokuApp;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequest12 extends TestBaseRestfulBooker {
    /*

https://restful-booker.herokuapp.com/booking/1 url ine bir istek gönderildiğinde
 dönen response body nin
  {"firstname": "Eric",
   "lastname": "Smith",
   "totalprice": 555,
   "depositpaid": false,
   "bookingdates": {
       "checkin": "2016-09-09",
       "checkout": "2017-09-21"
    }
    } gibi olduğunu test edin.
     */
    @Test
    public void test01() {
        //url olustur
        spec01.pathParams("parametre","booking","parametre2",1);

        //expected
        TestDataHerokuApp testData=new TestDataHerokuApp();
        HashMap<String,Object>expectedDataMap=testData.setUpTestData();

        System.out.println(expectedDataMap);
        //request gonder
        Response response=given().
                accept("application/json").
                spec(spec01).when().
                get("/{parametre}/{parametre2}");

        response.prettyPrint();


        //De-serialization
        HashMap<String,Object>acceptedData=response.as(HashMap.class);

        System.out.println(acceptedData);

       //assertion işlemi
        Assert.assertEquals(expectedDataMap.get("firstname"),acceptedData.get("firstname"));
        Assert.assertEquals(expectedDataMap.get("lastname"),acceptedData.get("lastname"));
        Assert.assertEquals(expectedDataMap.get("totalprice"),acceptedData.get("totalprice"));
        Assert.assertEquals(expectedDataMap.get("depositpaid"),acceptedData.get("depositpaid"));
        Assert.assertEquals(((Map)expectedDataMap.get("bookingdates")).get("checkin"),
                ((Map)acceptedData.get("bookingdates")).get("chekin"));
        Assert.assertEquals(((Map)expectedDataMap.get("bookingdates")).get("chekout"),
                ((Map)acceptedData.get("bookingdates")).get("chekout"));



    }
}
