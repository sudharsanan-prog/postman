//package org.example.TestNG.TestngTest;
//
//import io.restassured.RestAssured;
//import io.restassured.path.json.JsonPath;
//import io.restassured.response.Response;
//import org.example.TestNG.usablities.Payload;
//import org.example.TestNG.usablities.ResuableMethods;
//import org.testng.annotations.DataProvider;
//import org.testng.annotations.Test;
//
//import static io.restassured.RestAssured.given;
//
//public class DynamicJson {
//
//    @Test(dataProvider = "dummyData")
//    public void addBook(String aisle, String number){
//
//        RestAssured.baseURI="http://216.10.245.166";
//
//        String addBook = given().relaxedHTTPSValidation().log().all().header("Content-Type","application/json")
//                .body(Payload.addBook(aisle,number))
//                .when().post("Library/Addbook.php")
//                .then().log().all().assertThat().statusCode(200)
//                .extract().response().asString();
//
//
////        Response deleteBook = given().relaxedHTTPSValidation().log().all().header("Content-Type","application/json")
////                .when().post("/Library/DeleteBook.php")
////                .then().log().all().assertThat().statusCode(200)
////                .extract().response();
//
//
//
//        JsonPath js = ResuableMethods.RawStringToJson(addBook);
//        String addBookId = js.get("ID");
//        System.out.println(addBookId);
//    }
//
//    //DataProvider in TestNG
//
//    @DataProvider(name = "dummyData")
//    public Object[][] getData(){
//
//        return new Object[][] {{"bjkn","1234"},{"ikun","1235"},{"uiiu","1236"}};
//
//    }
//}

package org.example.TestNG.TestngTest;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.example.TestNG.usablities.Payload;
import org.example.TestNG.usablities.ResuableMethods;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class StaticJson {

    @Test
    public void addBook() throws IOException {

        RestAssured.baseURI = "http://216.10.245.166";

        // Add book request
        String addBookResponse = given()
                .relaxedHTTPSValidation()
                .log().all()
                .header("Content-Type", "application/json")
                //we are adding the string body from the external Json file
                //for that we require to convert the json to byte and then convert to string
                // so we are using the java command files.readAllBytes(Path.get("path of the json file"))
                //and converting the by to string by adding new String
                .body(new String (Files.readAllBytes(Paths.get("C:\\Users\\S122\\Downloads\\courses\\Postman-API\\RestAssured\\src\\main\\java\\org\\example\\TestNG\\jsonFile\\TestBodyJson.json"))))
                .when()
                .post("Library/Addbook.php")
                .then()
                .log().all()
                .assertThat().statusCode(200)
                .extract().response().asString();

        // Check if response is not empty before parsing
        if (addBookResponse != null && !addBookResponse.trim().isEmpty()) {
            JsonPath jsonPath = ResuableMethods.RawStringToJson(addBookResponse);
            String bookId = jsonPath.getString("ID");
            System.out.println("Book ID: " + bookId);

            // Assert that book ID is not null
            Assert.assertNotNull(bookId, "Book ID should not be null");
        }
    }
}
