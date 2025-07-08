package org.example.TestNG.TestngTest;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.example.TestNG.PojoClass;
import org.example.TestNG.PojoSub1;
import org.example.TestNG.PojoSub2;
import org.example.TestNG.usablities.ResuableMethods;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class OAuthAuthorizationPojo {

    @Test
    public void Auth(){

        String[] courseTitles = {"Selenium Webdriver Java","Cypress", "Protractor"};

        RestAssured.baseURI = "https://rahulshettyacademy.com/";

        String Token = given().relaxedHTTPSValidation().log().all()
                .param("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .param("client_secret","erZOWM9g3UtwNRj340YYaK_W")
                .param("grant_type","client_credentials")
                .param("scope","trust")
                .when().post("oauthapi/oauth2/resourceOwner/token")
                .then().log().all().assertThat().statusCode(200).extract().response().asString();

        JsonPath js = ResuableMethods.RawStringToJson(Token);
        String accessToken = js.getString("access_token");
        System.out.println(accessToken);

        //using token to retrieve the data
        PojoClass course = given().relaxedHTTPSValidation().log().all().queryParam("access_token",accessToken)
                .when().get("oauthapi/getCourseDetails").as(PojoClass.class);//this passes the output to the Pojo classes and it traverses to the subclass to get the result
        //this uses the POJO class(getters and setters to get the json and return it as string using it)
        //we don't require JsonPath to parse now we can achieve it by creating class by POJO methodology
        String courseUrl = course.getUrl();
        System.out.println(courseUrl); //this process of breaking down the json to our requirement is known as de-serialization

        System.out.println(course.getCourses().getApi().get(1).getCourseTitle());
        List<PojoSub2> courseApi = course.getCourses().getApi();

        for (PojoSub2 pojoSub2 : courseApi) {
            if (pojoSub2.getCourseTitle().equals("SoapUI Webservices testing")) {
                String coursePrice = pojoSub2.getPrice();
                System.out.println("The course Price of \"" + pojoSub2.getCourseTitle() + "\" is :" + coursePrice);
            }

//        for(int i = 0; i < courseApi.size(); i++){
//            if(courseApi.get(i).getCourseTitle().equals("SoapUI Webservices testing")){
//                String coursePrice = courseApi.get(i).getPrice();
//                System.out.println("The course Price of " +courseApi.get(i).getCourseTitle()+ " is :"+coursePrice);
//            }

            ArrayList<String> arrayList = new ArrayList<>();

            //assignment to print all the course names of Automation
            List<PojoSub1> courseAutomation = course.getCourses().getWebAutomation();
            for(PojoSub1 pojoSub1: courseAutomation){

               arrayList.add(pojoSub1.getCourseTitle());
            }

            //converting array to list to compare with ArrayList
            List<String> expectedCourseArrays = Arrays.asList(courseTitles);
            Assert.assertEquals(arrayList, expectedCourseArrays);
        }
    }
}
