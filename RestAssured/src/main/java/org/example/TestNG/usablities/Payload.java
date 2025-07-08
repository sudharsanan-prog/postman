package org.example.TestNG.usablities;

public class Payload {

    public static String AddPlace(){
        //body of the add place and pasted in the
        return "{\n" +
                "\t\"location\":{\n" +
                "    \t\"lat\" : -38.383494,\n" +
                "    \t\"lng\" : 33.427362\n" +
                "\t},\n" +
                "\t\"accuracy\":50,\n" +
                "\t\"name\":\"Frontline house\",\n" +
                "\t\"phone_number\":\"(+91) 983 893 3937\",\n" +
                "\t\"address\" : \"29, side layout, cohen 09\",\n" +
                "\t\"types\": [\"shoe park\",\"shop\"],\n" +
                "\t\"website\" : \"http://google.com\",\n" +
                "\t\"language\" : \"French-IN\"\n" +
                "}\n";
    }


    ///creating dummy API for the code ////
  public static String CoursePrice(){
      return "{\n" +
              "\"dashboard\": {\n" +
              "\"purchaseAmount\": 910,\n" +
              "\"website\": \"rahulshettyacademy.com\"\n" +
              "},\n" +
              "\"courses\": [\n" +
              "{\n" +
              "\"title\": \"Selenium Python\",\n" +
              "\"price\": 50,\n" +
              "\"copies\": 6\n" +
              "},\n" +
              "{\n" +
              "\"title\": \"Cypress\",\n" +
              "\"price\": 40,\n" +
              "\"copies\": 4\n" +
              "},\n" +
              "{\n" +
              "\"title\": \"RPA\",\n" +
              "\"price\": 45,\n" +
              "\"copies\": 10\n" +
              "}\n" +
              "]\n" +
              "}\n";
  }

  public static String addBook(String aisle, String isbn){
      return
              "{\n" +
"\"name\": \"Learn Appium Automation with Java\",\n" +
 " \"isbn\": \"" + isbn + "\",\n" +
" \"aisle\": \"" + aisle + "\",\n" +
 " \"author\": \"John foe\"\n" +
 "}";

  }
}
