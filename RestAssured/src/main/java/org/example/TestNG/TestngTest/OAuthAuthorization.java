package org.example.TestNG.TestngTest;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.example.TestNG.usablities.ResuableMethods;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class OAuthAuthorization {

    @Test
    public void Auth(){

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
        given().relaxedHTTPSValidation().log().all().queryParam("access_token",accessToken)
                .when().get("oauthapi/getCourseDetails")
                .then().log().all().assertThat().statusCode(401);






    }
}
