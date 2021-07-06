package com.techproed.testData;

import java.util.HashMap;

public class TestDataHerokuApp {
    /*
     {
         "firstname": "Eric",
             "lastname": "Smith",
             "totalprice": 555,
             "depositpaid": false,
             "bookingdates": {
         "checkin": "2016-09-09",
                 "checkout": "2017-09-21"
     }
     } gibi olduğunu test edin

    */

    public HashMap<String, Object> setUpTestData() {

        HashMap<String,String>bookingdates=new HashMap<String,String>();
        bookingdates.put("checkin","2018-05-20");
        bookingdates.put("checkout","2018-07-26");

        //bookingdates içerisinde ayrı bir map yapısı vardır. bu sebeple bu kısım ayrı map oluşturulur
        // mapin type i String, String olur çünkü checkin ve checout değerleri Stringtir.

        HashMap<String,Object>expectedTestData=new HashMap<String,Object>();
        expectedTestData.put("firstname","Sally");
        expectedTestData.put("lastname","Brown");
        expectedTestData.put("totalprice",484);
        expectedTestData.put("bookingdates",bookingdates);

        return expectedTestData;


    }
}