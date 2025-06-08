package org.testing.base;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testing.asserts.AssertActions;
import org.testing.endpoint.APIConstants;
import org.testing.modules.PayloadManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public class BaseTest {
    // CommonToAll Testcase
    //   // Base URL, Content Type - json - common
public PayloadManager payloadManager;
public Response response;
public ValidatableResponse validatableResponse;
public JsonPath jsonPath;
public RequestSpecification requestSpecification;
public AssertActions assertActions;




    @BeforeMethod
    public void Setup()
    {
        System.out.println("Start");
        payloadManager= new PayloadManager();
        assertActions=new AssertActions();
//        requestSpecification= RestAssured.given();
//        requestSpecification.baseUri(APIConstants.BASE_URL);
//        requestSpecification.contentType(ContentType.JSON).log().all();


        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(APIConstants.BASE_URL)
                .addHeader("Content-Type", "application/json")
                .build().log().all();




    }

    public String getToken()
    {
        requestSpecification =RestAssured.given();
        requestSpecification.baseUri(APIConstants.BASE_URL)
                .basePath(APIConstants.AUTH_URL);
        //Setting payload
        String payload= payloadManager.setAuthPayload();
        //Get the token
        response= requestSpecification.contentType(ContentType.JSON).body(payload).when().post();

        String token= payloadManager.getTokenFromJSON(response.asString());
        return  token;

    }

    @AfterMethod
    public void tearDown()
    {
        System.out.println("Finish");
    }
}
