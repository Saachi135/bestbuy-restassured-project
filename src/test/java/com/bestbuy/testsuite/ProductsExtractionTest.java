package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ProductsExtractionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {


        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/products")

                // this is validatable data
                .then().statusCode(200);
    }

    //  21. Extract the limit
    @Test
    public void Test01() {
        int limit = response.extract().path("limit");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value limit is :" + limit);
        System.out.println("------------------End of Test---------------------------");
    }

    //  22. Extract the total
    @Test
    public void Test02() {
        int total = response.extract().path("total");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Total value total is :" + total);
        System.out.println("------------------End of Test---------------------------");
    }

    //  23. Extract the name of 5th product
    @Test
    public void Test03() {
        String name = response.extract().path("data[4].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("5th Product name is:" + name);
        System.out.println("------------------End of Test---------------------------");
    }

    //  24. Extract the names of all the products
    @Test
    public void Test04() {
        List<String> nameAll = response.extract().path("data.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Extract the Name of Products " + nameAll);
        System.out.println("------------------End of Test---------------------------");
    }

    //  25. Extract the productId of all the products
    @Test
    public void Test05() {
        List<Integer> productID = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Extract the ID of Products " + productID);
        System.out.println("------------------End of Test---------------------------");
    }

    //  26. Print the size of the data list
    @Test
    public void Test06() {
        List<Integer> dataList = response.extract().path("data");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Size of Total Size: " + dataList);
        System.out.println("------------------End of Test---------------------------");
    }

    //  27. Get all the value of the product where product name = Energizer - MAX Batteries AA (4-Pack)
    @Test
    public void Test07() {
        List<HashMap<String, ?>> values = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All values are :"+ values);
        System.out.println("------------------End of Test---------------------------");
    }

    //  28. Get the model of the product where product name = Energizer - N Cell E90 Batteries (2-Pack)
    @Test
    public void Test08() {
        List<HashMap<String, ?>> model = response.extract().path("data.findAll{it.name == 'Energizer - N Cell E90 Batteries (2-Pack)'}.model");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Model Name of Energizer - N Cell E90 Batteries (2-Pack): " + model);
        System.out.println("------------------End of Test---------------------------");
    }

    //  29. Get all the categories of 8th products
    @Test
    public void Test09() {
        List<String> categories = response.extract().path("data.findAll{it.categories}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Get All Categories: " + categories);
        System.out.println("------------------End of Test---------------------------");
    }

    //  30. Get categories of the store where product id = 150115
    @Test
    public void Test10() {
        List<String> categoriesStore = response.extract().path("data[3].categories");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Get categories of the store where product id = 150115: " + categoriesStore);
        System.out.println("------------------End of Test---------------------------");
    }

    //  31. Get all the descriptions of all the products
    @Test
    public void Test11() {
        List<HashMap<String, ?>> descriptions = response.extract().path("data.description");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Description of all the products are :" + descriptions);
        System.out.println("------------------End of Test---------------------------");

    }

    //  32. Get id of all the all categories of all the products
    @Test
    public void test12() {
        List<HashMap<String, ?>> ids = response.extract().path("data.categories.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Get All Ids: " + ids);
        System.out.println("------------------End of Test---------------------------");
    }
    //  33. Find the product names Where type = HardGood
    @Test
    public void Test13(){
        List<HashMap<String,?>>type = response.extract().path("data.findAll{it.type == 'HardGood'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Product name where type is hardGood: " + type);
        System.out.println("------------------End of Test---------------------------");
    }


    //  34. Find the Total number of categories for the product where product name = Duracell - AA 1.5V CopperTop Batteries (4-Pack)
    @Test
    public void Test14(){
        List<?> totalService = response.extract().path("data.find{it.name == 'Duracell - AA 1.5V CopperTop Batteries (4-Pack)'}.categories");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All Categories Total is: " + totalService.size());
        System.out.println("------------------End of Test---------------------------");

    }
    //  35. Find the createdAt for all products whose price < 5.49
    @Test
    public void Test15(){
        List<String> createdAt = response.extract().path("data.findAll{it.price < 5.49}.createdAt");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All product created at whose price is 5.49 : " +createdAt );
        System.out.println("------------------End of Test---------------------------");

    }
    //  36. Find the name of all categories Where product name = “Energizer - MAX Batteries AA (4-Pack)”
    @Test
    public void Test16(){
        List<HashMap<String,?>> allCategories = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}.categories.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All categories where product name is “Energizer - MAX Batteries AA (4-Pack)”  : " + allCategories );
        System.out.println("------------------End of Test---------------------------");
    }
    //  37. Find the manufacturer of all the products
    @Test
    public void Test17(){
        List<HashMap<String ,?>> allManufacturer = response.extract().path("data.findAll{it.name}.manufacturer");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All product manufacturer are : " + allManufacturer );
        System.out.println("------------------End of Test---------------------------");
    }
    //  38. Find the imge of products whose manufacturer is = Energizer
    @Test
    public void Test18(){
        List<HashMap<String, ?>> image = response.extract().path("data.findAll{it.manufacturer == 'Energizer'.image }");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Image of product whose manufacturer is energizer : " + image );
        System.out.println("------------------End of Test---------------------------");
    }
    //  39. Find the createdAt for all categories products whose price > 5.99
    @Test
    public void Test19(){
        List<HashMap<String ,?>> createdAt = response.extract().path("data.findAll{it.price > 5.99}.createdAt");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All categories product whose price is >5.99 : " + createdAt );
        System.out.println("------------------End of Test---------------------------");
    }
    //  40. Find the uri of all the products
    @Test
    public void Test20(){
        List<String> url = response.extract().path("data.url");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All url of products : " + url );
        System.out.println("------------------End of Test---------------------------");
    }

}
