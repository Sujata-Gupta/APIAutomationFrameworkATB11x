package org.testing.tests.crud;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.testing.base.BaseTest;
import org.testing.endpoint.APIConstants;
import org.testng.annotations.Test;

import java.util.Base64;

public class TestCreateToken extends BaseTest {

    @Test( groups ="reg",priority =1)
    @Owner("Sujata")
    @Description("TC#2 - Create Token and Verify")
    public void testTokenPOST()
    {
        requestSpecification.basePath(APIConstants.AUTH_URL);
        response = RestAssured.given(requestSpecification).body(payloadManager.setAuthPayload()).post();


        String token= payloadManager.getTokenFromJSON(response.asString());




        assertActions.verifyStringKeyNotNull(token);
    }
}