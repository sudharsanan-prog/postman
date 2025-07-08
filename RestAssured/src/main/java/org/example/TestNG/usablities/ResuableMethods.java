package org.example.TestNG.usablities;

import io.restassured.path.json.JsonPath;

public class ResuableMethods {

    public static JsonPath RawStringToJson(String JsonObj){
        return new JsonPath(JsonObj);
    }
}
