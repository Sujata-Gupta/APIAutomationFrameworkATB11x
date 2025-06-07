package org.testing.tests.crud;

import com.google.common.base.Verify;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import org.testing.base.BaseTest;
import org.testing.endpoint.APIConstants;
import org.testng.annotations.Test;

public class TestHealthCheck extends BaseTest {

    @Test
    @Description("TC#3 - Verify Health")
    public void testGetHealthCheck()
    {
        requestSpecification.basePath(APIConstants.PING_URL);
        response = RestAssured.given(requestSpecification).when().get();


        validatableResponse =response.then().log().all();
        validatableResponse.statusCode(201);
    }
}
