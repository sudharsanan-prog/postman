package org.example.TestNG;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.example.TestNG.usablities.Payload;

import static io.restassured.RestAssured.*;

import org.example.TestNG.usablities.ResuableMethods;
import org.testng.Assert;

import static org.hamcrest.Matchers.*;


public class Rest {
    public static void main(String[] args) {

        //Rest contains 3 methods GIVEN, WHEN, THEN  to write the test case and coding
        //REQUIREMENT
        //To add Place the API
        //we are giving the base url
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        //In Postman params we will give key and select content type as JSON and pasting the body contents.
        //Here we are automating the code with Java
        //Resource and the http method will come under
        //In body Java is taking the parameter as String, it cannot understand the JSON
        //log.all which logs all the given log statements and also we have add for then for getting the final outcome
        //relaxedHTTPSValidation() is used to bypass the validation of ssl certificate
//       given().relaxedHTTPSValidation().log().all().queryParam("Key","qaclick123").header("Content-Type","application/json").
//               body(Payload.AddPlace()).when().post("/maps/api/place/add/json").then().log().all().assertThat().statusCode(200).
//               //validate whether the "scope": "APP" in the console.(equalTo is coming from Hamcrest Package)
//               body("scope",equalTo("APP")).
//              //this header is for output validation, previous header is for the input
//               header("Server","Apache/2.4.52 (Ubuntu)");
//
        //Here we are taking out the log() method from the end instead adding the extracted result to string variable and printing by using that variable.
        String output = given().relaxedHTTPSValidation().log().all().queryParam("Key", "qaclick123").header("Content-Type", "application/json").
                body(Payload.AddPlace()).when().post("/maps/api/place/add/json").then().
                assertThat().statusCode(200).
                //validate whether the "scope": "APP" in the console.(equalTo is coming from Hamcrest Package)
                        body("scope", equalTo("APP")).
                //this header is for output validation, previous header is for the input
                        header("Server", "Apache/2.4.52 (Ubuntu)").
                extract().response().asString();

       // System.out.println(output);

//Objective: Our main objective is to use the "place_id" from the console and use it for other operations
        //but the place_id in output was a json, so we need to convert the json to string
        //for that we are using JsonPath which parse the String to Json by passing the entire result String variable to the JsonPath
        JsonPath jp = new JsonPath(output);
        //In our case there is no parent so we put the direct "place_id"
        //If the json contain parent so we must mention "parent.child"
        String PlaceId = jp.getString("place_id");
       // System.out.println(PlaceId);

        //update
        String newAddress = "70 Summer walk, USA";
               given().relaxedHTTPSValidation().log().all().queryParam("key","qaclick123").
               //we are adding the id of the place to the body
                header("Content-Type","application/json").
               body("{\n" +
                       "\"place_id\":\""+PlaceId+"\",\n" +
                       "\"address\":\""+newAddress+"\",\n" +
                       "\"key\":\"qaclick123\"\n" +
                       "}\n").
                when().put("/maps/api/place/update/json").
                then().assertThat().log().all().statusCode(200).body("msg",equalTo("Address successfully updated")).toString();

       //checking Address is updated
        //there is no necessity for header and the body to retrieve the data
        String getPlace = given().relaxedHTTPSValidation().log().all().queryParam("key", "qaclick123")
                .queryParam("place_id", PlaceId)
                .when().get("/maps/api/place/get/json")
                //we always use toString but here we will use asString to convert to string
                .then().log().all().assertThat().statusCode(200).extract().response().asString();


        //enhanced json path by reusablities
        JsonPath Json = ResuableMethods.RawStringToJson(getPlace);
        String updAdd = Json.getString("address");

        //testNG validation
        Assert.assertEquals(updAdd,newAddress);

        //Normal validation
        if(updAdd.equals("70 Summer walk, USA")){
            System.out.println("The Address is Matching.");
            System.out.println("Address: "+updAdd);
        }






    }
}
