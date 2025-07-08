package org.example.TestNG.TestngTest;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.example.TestNG.usablities.ResuableMethods;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class JiraApi {

    @Test
    public void JiraRest(){

        RestAssured.baseURI="https://sudharsananrajavelu.atlassian.net/";

      String createTicket =  given().relaxedHTTPSValidation().log().all().header("Content-Type","application/json")
                .header("Authorization","Basic c3VkaGFyc2Fuczc2MkBnbWFpbC5jb206QVRBVFQzeEZmR0YwbW0yZkd5M3Judm56b2EySkw4UEJMYWVHWnRtUG5uZnlLTldqTVVFVGQyaTExY0c4ekM4ejJIQS1GT1ZoTUZsR19tc0F1enNKNmtCNmRGd2ZLT1RRS1F2VU9WUUxWVklBTjJlcndXc3ZRQWdjM0JCWHp4MS1pSTZKeVI3NUtuTlI2X2cwX090Y2hQd1FvWGptdnAwVEZGalVJU2p6MHVVNjBXTHE5dHZKcmtvPUFEM0RDQzE5")
                .header("Accept","*/*").body("{\n" +
                        "    \"fields\": {\n" +
                        "       \"project\":\n" +
                        "       {\n" +
                        "          \"key\": \"SCRUM\"\n" +
                        "       },\n" +
                        "       \"summary\": \"Aaradhana new ticket\",\n" +
                        "       \"description\": \"Creating of an issue using project keys and issue type names using the REST API\",\n" +
                        "       \"issuetype\": {\n" +
                        "          \"name\": \"Bug\"\n" +
                        "       }\n" +
                        "   }\n" +
                        "}").when().post("rest/api/2/issue")
              .then().assertThat().statusCode(201).extract().response().asString();

      JsonPath js = ResuableMethods.RawStringToJson(createTicket);
      String ticketId = js.getString("id");
      System.out.println(ticketId);

      //adding attachments

        given().relaxedHTTPSValidation().log().all().pathParam("ticketIdp",ticketId)
                .header("Authorization","Basic c3VkaGFyc2Fuczc2MkBnbWFpbC5jb206QVRBVFQzeEZmR0YwbW0yZkd5M3Judm56b2EySkw4UEJMYWVHWnRtUG5uZnlLTldqTVVFVGQyaTExY0c4ekM4ejJIQS1GT1ZoTUZsR19tc0F1enNKNmtCNmRGd2ZLT1RRS1F2VU9WUUxWVklBTjJlcndXc3ZRQWdjM0JCWHp4MS1pSTZKeVI3NUtuTlI2X2cwX090Y2hQd1FvWGptdnAwVEZGalVJU2p6MHVVNjBXTHE5dHZKcmtvPUFEM0RDQzE5")
                .header("X-Atlassian-Token","no-check")
                //for adding new file(png, txt, csv,.. we required to use multipart to attach file in the id of the bug)
                .multiPart("file",new File("C:\\Users\\S122\\Downloads\\Illegal_access_error.png"))
                .when().post("rest/api/2/issue/{ticketIdp}/attachments")
                .then().assertThat().statusCode(200);
    }

}
