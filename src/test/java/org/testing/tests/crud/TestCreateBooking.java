package org.testing.tests.crud;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.testing.base.BaseTest;
import org.testing.endpoint.APIConstants;
import org.testing.pojos.response.BookingResponse;
import org.testng.annotations.Test;

public class TestCreateBooking extends BaseTest {

    @Test(groups ="reg", priority=1)
    @Owner("Sujata")
    @Description("TC#1 - verify that the booking can be created")
    public void testCreateBookingPOST_Positive()
    {
        //Setup
       requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
       response = RestAssured.given(requestSpecification).when().body(payloadManager.createPayloadBookingAsString()).post();


       //extraction deserilization
        BookingResponse bookingResponse= payloadManager.bookingResponseJava(response.asString());

        //Assertion

        assertActions.verifyStringKey(bookingResponse.getBooking().getFirstname(),"Sujata");
        assertActions.verifyStringKeyNotNull(bookingResponse.getBookingid());
        //assertActions.verifyStatusCode(response.getStatusCode(),200);
        //validatableResponse.statusCode(200);

    }

    @Test(groups = "reg", priority = 2)
    @Owner("Sujata")
    @Description("TC#1 - verify that the booking cannot be created when payload is null")
    public void testCreateBookingPOST_Negative()
    {
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response = RestAssured.given(requestSpecification).when().body("{}").post();


       validatableResponse=response.then().log().all();
        validatableResponse.statusCode(500);

    }
        @Test(groups ="reg", priority=1)
        @Owner("Sujata")
        @Description("TC#1 - verify that the booking can be created with random")
        public void testCreateBookingPOST_Positive_RANDOM_DATA()
        {
            //Setup
            requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
            response = RestAssured.given(requestSpecification).when().body(payloadManager.createPayloadBookingFakerJS()).post();


            //extraction deserilization
            BookingResponse bookingResponse= payloadManager.bookingResponseJava(response.asString());

            //Assertion

            assertActions.verifyStringKey(bookingResponse.getBooking().getFirstname(),"Sujata");
            assertActions.verifyStringKeyNotNull(bookingResponse.getBookingid());
            //assertActions.verifyStatusCode(response.getStatusCode(),200);
            //validatableResponse.statusCode(200);

        }

    }
