package org.example.TestNG.TestngTest;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.example.TestNG.TestngTest.EcommerceSub.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ECommerceAPIE2E {

    @Test
    public void CRUD() {

        // Base request specification
        RequestSpecification baseSpec = new RequestSpecBuilder()
                .setRelaxedHTTPSValidation()
                .setBaseUri("https://rahulshettyacademy.com")
                .setContentType(ContentType.JSON)
                .build();

        // Login
        LoginCredentials login = new LoginCredentials();
        login.setUserEmail("email07@example.com");
        login.setUserPassword("Rahul@123");

        LoginResponse loginResponse = given().log().all().spec(baseSpec).body(login)
                .when().post("/api/ecom/auth/login")
                .then().extract().response().as(LoginResponse.class);

        String token = loginResponse.getToken();
        String userId = loginResponse.getUserId();

        // Add Product
        RequestSpecification addProductSpec = new RequestSpecBuilder()
                .setRelaxedHTTPSValidation()
                .setBaseUri("https://rahulshettyacademy.com")
                .addHeader("Authorization", token)
                .build();

        String addProductResponse = given().spec(addProductSpec)
                .param("productName", "qwerty")
                .param("productAddedBy", userId)
                .param("productCategory", "fashion")
                .param("productSubCategory", "shirts")
                .param("productPrice", "11500")
                .param("productDescription", "Adidas Originals")
                .param("productFor", "women")
                .multiPart("productImage", new File("C:\\Users\\S122\\Downloads\\Screenshot 2025-07-01 152252.png"))
                .when().post("/api/ecom/product/add-product")
                .then().log().all().extract().asString();

        JsonPath js = new JsonPath(addProductResponse);
        String productId = js.getString("productId");
        System.out.println("Product ID: " + productId);

        // Create Order
        RequestSpecification orderSpec = new RequestSpecBuilder()
                .setRelaxedHTTPSValidation()
                .setBaseUri("https://rahulshettyacademy.com")
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", token)
                .build();

        OrderRequestDetails orderDetails = new OrderRequestDetails();
        orderDetails.setCountry("India");
        orderDetails.setProductOrderedId(productId); // Make sure this maps to "productOrderedId" in JSON

        List<OrderRequestDetails> orderList = new ArrayList<>();
        orderList.add(orderDetails);

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setOrders(orderList);



        RequestSpecification createOrderReq = given().spec(orderSpec).body(orderRequest);

         String createOrderRes = createOrderReq.when().post("/api/ecom/order/create-order")
                .then().log().all().extract().response().asString();

        System.out.println("Create Order Response: " + createOrderRes);


        //Delete Order
        RequestSpecification DeleteSpec = new RequestSpecBuilder()
                .setRelaxedHTTPSValidation()
                .setBaseUri("https://rahulshettyacademy.com")
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", token)
                .build();

        RequestSpecification delReqSpec = given().spec(DeleteSpec)
                .pathParam("productId", productId);

        String deleteProductResponse = delReqSpec.when()
                .delete("/api/ecom/product/delete-product/{productId}")
                .then().log().all().extract().asString();

        JsonPath js1 = new JsonPath(deleteProductResponse);
        Assert.assertEquals(js1.getString("message"), "Product Deleted Successfully");

    }

}
