package com.techproed.day08;

import com.techproed.testBase.TestBaseDummy;
import com.techproed.testData.TestDataDummy;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Getrequest13 extends TestBaseDummy {
    /*
    http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
Status kodun 200 olduğunu,
5. Çalışan isminin "Airi Satou" olduğunu ,  çalışan sayısının 24 olduğunu,
Sondan 2. çalışanın maaşının 106450 olduğunu
40,21 ve 19 yaslarında çalışanlar olup olmadığını
11. Çalışan bilgilerinin
  {
 “id”:”11”
 "employee_name": "Jena Gaines",
"employee_salary": "90560",
"employee_age": "30",
"profile_image": "" }
} gibi olduğunu test edin.
     */
    @Test
    public  void test01(){
        //url ulasmak
        spec03.pathParam("parametre","employees");
        //response.prettyPrint();
        Response response=given().
                accept("application/json").
                spec(spec03).
                when().
                get("/{parametre}");
        response.prettyPrint();

        //expected
         TestDataDummy testData=new TestDataDummy();
         HashMap<String,Object>expectedDataMap=testData.setUpTestData();

        System.out.println(expectedDataMap);
        //De-serialization islemi
        HashMap<String,Object>actualData=response.as(HashMap.class);


        //assertion

        Assert.assertEquals(expectedDataMap.get("statusCode"),response.getStatusCode());
        Assert.assertEquals(expectedDataMap.get("besinciCalisanIsim"),
                ((Map)((List)actualData.get("data")).get(4)).get("employee_name"));
        Assert.assertEquals(expectedDataMap.get("calisanSayi"),
                ((List)actualData.get("data")).size());
        int dataSize=((List<?>) actualData.get("data")).size();

        Assert.assertEquals(expectedDataMap.get("sondanIkiMaasi"),
               ((Map)((List<?>)actualData.get("data")).get(dataSize-2)).get("employee_salary"));

        List<Integer> actualYasList=new ArrayList<Integer>();
        for(int i=0; i<dataSize; i++){
            actualYasList.add((Integer)((Map)((List<?>) actualData.get("data")).get(i)).get("employee_age"));
        }
        Assert.assertTrue(actualYasList.containsAll((List)expectedDataMap.get("yasListesi")));
        Assert.assertEquals(
                ((Map)expectedDataMap.get("onbirinciId")).get("id"),
                ((Map) ((List<?>) actualData.get("data")).get(10)).get("id")
        );
        Assert.assertEquals(
                ((Map)expectedDataMap.get("onbirinciId")).get("profile_image"),
                ((Map) ((List<?>) actualData.get("data")).get(10)).get("profile_image")
        );
        Assert.assertEquals(
                ((Map)expectedDataMap.get("onbirinciId")).get("employee_name"),
                ((Map) ((List<?>) actualData.get("data")).get(10)).get("employee_name")
        );
        Assert.assertEquals(
                ((Map)expectedDataMap.get("onbirinciId")).get("employee_salary"),
                ((Map) ((List<?>) actualData.get("data")).get(10)).get("employee_salary")
        );
        Assert.assertEquals(
                ((Map)expectedDataMap.get("onbirinciId")).get("employee_age"),
                ((Map) ((List<?>) actualData.get("data")).get(10)).get("employee_age")
        );


    }
}
