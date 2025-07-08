package org.example.TestNG;

import io.restassured.path.json.JsonPath;
import org.example.TestNG.usablities.Payload;
import org.testng.Assert;

import java.util.Objects;

public class ComplexJsonParse {

    public static void main(String[] args) {


        //This test is to validate the nested json file
//Criteria to be achived
//1. Print No of courses returned by API
//2.Print Purchase Amount
//3. Print Title of the first course
//4. Print All course titles and their respective Prices
//5. Print no of copies sold by RPA Course
//6. Verify if Sum of all Course prices matches with Purchase Amount

//Note: When the API was not ready from the developer, we have to test some dummy data of exact match to complete the coding before the API reach to us

        JsonPath js = new JsonPath(Payload.CoursePrice());
        //1. Print No of courses returned by API
        //we have to get the json values so we are passing string json type to Json and getting the attributes
        //Here our aim to get the size of the course, so we are using the method getInt
        //the operation to perform should be defined in a string format.
        int count = js.getInt("courses.size()");
        System.out.println(count);

        //2.Print Purchase Amount
        int PurchaseAmt = js.getInt("dashboard.purchaseAmount");
        System.out.println(PurchaseAmt);

        //3. Print Title of the first course
        //get method will default get the string value.
        //In order to fetch the array data we have to give parent[index].key]
        String courseTitle = js.get("courses[0].title");
        System.out.println(courseTitle);//value is printed as output

        //4. Print All course titles and their respective Prices
        //we are taking the count from point 1 and looping to that extent and giving the loop value to the json dynamically
        for(int i = 0; i < count; i++){
            System.out.println(js.getString("courses["+i+"].title"));
            System.out.println(js.getString("courses["+i+"].price"))    ;
        }

        //5. Print no of copies sold by RPA Course\
        for(int i = 0; i < count; i++){
            String CourseTitle = js.get("courses["+i+"].title");
            if(Objects.equals(CourseTitle, "RPA")){
            System.out.println(js.getString("courses["+i+"].copies"));
            break;
            };
        }

        //6. Verify if Sum of all Course prices matches with Purchase Amount
        int sum = 0;
        for(int i = 0; i < count; i++){
            int price = js.getInt("courses["+i+"].price");
            int copies = js.getInt("courses["+i+"].copies");

            int amount = price *copies;
            sum+=amount;
        }
        System.out.println(sum);
        int totalPurchaseAmt =  js.getInt("dashboard.purchaseAmount");
        System.out.println(totalPurchaseAmt);

        Assert.assertEquals(totalPurchaseAmt,sum);
    }
}
