package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StoresExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {


        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/stores")

                // this is validatable data
                .then().statusCode(200);
    }

    //  1. Extract the limit
    @Test
    public void Test01() {
        int limit = response.extract().path("limit");
        System.out.println("The value of limit is : " + limit);
    }

    //  2. Extract the total
    @Test
    public void Test02() {
        int total = response.extract().path("total");
        System.out.println("The total value is :" + total);
    }

    //  3. Extract the name of 5th store
    @Test
    public void Test03() {
        String name = response.extract().path("data[4].name");
        System.out.println("Name of 5th store is:" + name);
    }

    //  4. Extract the names of all the store
    @Test
    public void Test04() {
        List<String> store = response.extract().path("data.name");
        System.out.println("Name of all the store :" + store);
    }

    //  5. Extract the storeId of all the store
    @Test
    public void Test05() {
        List<Integer> allStoreId = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The all store id is :" + allStoreId);
        System.out.println("------------------End of Test---------------------------");
    }

    //  6. Print the size of the data list
    @Test
    public void Test06() {
        List<HashMap> dataSize = response.extract().path("data");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Size of Data is : " + dataSize.size());
        System.out.println("------------------End of Test---------------------------");
    }

    //  7. Get all the value of the store where store name = St Cloud
    @Test
    public void Test07() {
        List<HashMap<String, ?>> valueofStore = response.extract().path("data.findAll{it.name == 'St Cloud'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Size of Data is : " + valueofStore);
        System.out.println("------------------End of Test---------------------------");
    }

    //  8. Get the address of the store where store name = Rochester
    @Test
    public void Test08() {
        List<String> address = response.extract().path("data.findAll{it.name == 'Rochester'}.address");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The address of store is : " + address);
        System.out.println("------------------End of Test---------------------------");
    }

    //  9. Get all the services of 8th store
    @Test
    public void Test09() {
        List<String> allServices = response.extract().path("data[7].services");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All services of 8th store : " + allServices);
        System.out.println("------------------End of Test---------------------------");
    }

    //  10. Get store services of the store where service name = Windows Store
    @Test
    public void Test10() {
        List<String> storeServices = response.extract().path("data.services.findAll{it.name == 'Windows Store'}.services");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Store Services : " + storeServices);
        System.out.println("------------------End of Test---------------------------");
    }

    //  11. Get all the storeId of all the store
    @Test
    public void Test11() {
        List<Integer> allStoreId = response.extract().path("data.services.storeservices.storeId");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Store IDs : " + allStoreId);
        System.out.println("------------------End of Test---------------------------");
    }

    //  12. Get id of all the store
    @Test
    public void Test12() {
        List<Integer> id = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Store IDs : " + id);
        System.out.println("------------------End of Test---------------------------");
    }

    //  13. Find the store names Where state = ND
    @Test
    public void Test13() {
        List<String> storeName = response.extract().path("data.findAll{it.state =='ND'.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Store Name : " + storeName);
        System.out.println("------------------End of Test---------------------------");

    }

    //  14. Find the Total number of services for the store where store name = Rochester
    @Test
    public void Test14() {
        List<?> totalServices = response.extract().path("data.findAll{it.name == 'Rochester'}.services");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The total Number of Services : " + totalServices.size());
        System.out.println("------------------End of Test---------------------------");
    }

    //  15. Find the createdAt for all services whose name = “Windows Store”
    @Test
    public void Test15() {
        List<?> createdAt = response.extract().path("data.find{it.services}.services.findAll{it.name == 'Windows Store'}storeservices.createdAt");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The total Number of Services : " + createdAt);
        System.out.println("------------------End of Test---------------------------");
    }

    //  16. Find the name of all services Where store name = “Fargo”
    @Test
    public void Test16() {
        List<HashMap<String, ?>> allServices = response.extract().path("data.findAll{it.name == 'Fargo'}.services");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Name of all services is :" + allServices);
        System.out.println("------------------End of Test---------------------------");

    }

    //  17. Find the zip of all the store
    @Test
    public void Test17() {
        List<HashMap<String, ?>> allZip = response.extract().path("data.zip");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Zip Number of All Store : " + allZip);
        System.out.println("------------------End of Test---------------------------");
    }

    //  18. Find the zip of store name = Roseville
    @Test
    public void Test18() {
        List<HashMap<String, ?>> zip = response.extract().path("data.findAll{it.name == 'Roseville'}.zip");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Zip Number of All Store : " + zip);
        System.out.println("------------------End of Test---------------------------");
    }

    //  19. Find the store services details of the service name = Magnolia Home Theater
    @Test
    public void Test19() {
        List<HashMap<String, ?>> storeServices = response.extract().path("data.services.findAll{it.name == 'Magnolia Home Theater'}.storeservices");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Store Service of Mongolia Home Theater : " + storeServices);
        System.out.println("------------------End of Test---------------------------");

    }

    //  20. Find the lat of all the stores
    @Test
    public void Test20() {
        List<HashMap<String,?>> lat = response.extract().path("data.lat");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The lat of All Store : " + lat);
        System.out.println("------------------End of Test---------------------------");

    }
}
