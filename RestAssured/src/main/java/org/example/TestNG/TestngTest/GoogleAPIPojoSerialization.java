package org.example.TestNG.TestngTest;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.example.TestNG.SerializationPojo.PojoClassSerialization;
import org.example.TestNG.SerializationPojo.PojoSubSerialization;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class GoogleAPIPojoSerialization {


    @Test
    public void Serialization(){

        //calling the class methods and setting the value
        PojoClassSerialization pojoSerial = new PojoClassSerialization();

        //for language we created sub class, so we are creating object for that class
        PojoSubSerialization location = new PojoSubSerialization();
        location.setLatitude(-38.383494);
        location.setLongitude(33.427362);
        pojoSerial.setLocation(location);//this gets the knowledge of the subclass values

        pojoSerial.setAccuracy(50);
        pojoSerial.setName("Frontline house");
        pojoSerial.setPhoneNumber("(+91) 983 893 3937");
        pojoSerial.setAddress("29, side layout, cohen 09");
        //for creating the multiple variable we are using ArrayList to store
        ArrayList<String> arrayVal = new ArrayList<>();
        arrayVal.add("shoe park");
        arrayVal.add("shop");
        pojoSerial.setTypes(arrayVal);
        pojoSerial.setWebsite("http://google.com");
        pojoSerial.setLanguage("French-IN");

        //we are adding request builder for minimizing the given code by parsing the reused code in request.
        RequestSpecification requestSpec = new RequestSpecBuilder()
                .setRelaxedHTTPSValidation()
                .setBaseUri("https://rahulshettyacademy.com")
                .addQueryParam("key", "qackick123")
                .setContentType(ContentType.JSON).build();

        ResponseSpecification responseSpec  = new ResponseSpecBuilder().expectStatusCode(200)
                .expectContentType(ContentType.JSON).build();


        //breaking the given by the RequestSpecification
        RequestSpecification responseReq = given().log().all().spec(requestSpec).body(pojoSerial);

          //Breaking the when and then in the Response return type
               Response response =  responseReq.when().post("/maps/api/place/add/json")
                .then().spec(responseSpec).extract().response();

        //breaking the extracting return type
        String responseString = response.toString();
        System.out.println(responseString);
    }
}
