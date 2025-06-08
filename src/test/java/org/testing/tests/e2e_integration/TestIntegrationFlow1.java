package org.testing.tests.e2e_integration;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testing.base.BaseTest;
import org.testing.endpoint.APIConstants;
import org.testing.pojos.request.Booking;
import org.testing.pojos.response.BookingResponse;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestIntegrationFlow1 extends BaseTest {

    // TestE2EFlow_01

    //  Test E2E Scenario 1

    //  1. Create a Booking -> bookingID

    // 2. Create Token -> token

    // 3. Verify that the Create Booking is working - GET Request to bookingID

    // 4. Update the booking ( bookingID, Token) - Need to get the token, bookingID from above request

    // 5. Delete the Booking - Need to get the token, bookingID from above request

    @Test(groups = "qa", priority = 1)
    @Owner("Sujata")
    @Description("TC#INT1 - Step 1. Verify that the Booking can be Created")
    public void testCreateBooking(ITestContext iTextContext)
    {
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response= RestAssured.given(requestSpecification).body(payloadManager.createPayloadBookingAsString()).post();
        validatableResponse =response.then().log().all();
        validatableResponse.statusCode(200);
        BookingResponse bookingResponse= payloadManager.bookingResponseJava(response.asString());
        assertActions.verifyStringKey(bookingResponse.getBooking().getFirstname(),"Sujata");
        assertActions.verifyStringKeyNotNull(bookingResponse.getBookingid());

        //iTextContext.setAttribute("bookingid",bookingResponse.getBookingid());
        iTextContext.setAttribute("bookingid",bookingResponse.getBookingid());


    }

    @Test(groups = "qa", priority = 2)
    @Owner("Sujata")
    @Description("TC#INT1- Step 2. Verify  the Booking by ID")
    public void testVerifyBookingId(ITestContext iTestContext) {
        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");
        System.out.println(bookingid);
        String basePathGET = APIConstants.CREATE_UPDATE_BOOKING_URL+"/" + bookingid;
        System.out.println(basePathGET);

        requestSpecification.basePath(basePathGET);
        response = RestAssured
                .given(requestSpecification)
                .when().get();
        validatableResponse = response.then().log().all();
        // Validatable Assertion
        validatableResponse.statusCode(200);

        Booking booking = payloadManager.getResponseFromJSON(response.asString());
        assertActions.verifyStringKeyNotNull(booking.getFirstname());



    }

        @Test(groups="qa", priority = 3)
        @Owner("Sujata")
        @Description("TC#INT1  Step 3- Verify updated booking By ID")
        public void testUpdatedBookingByID(ITestContext iTestContext)
        {

            Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");
            String token= getToken();
            iTestContext.setAttribute("token",token);
            String basePathPUTPATCH= APIConstants.CREATE_UPDATE_BOOKING_URL+"/"+bookingid;

            requestSpecification.basePath(basePathPUTPATCH);

            response =RestAssured.given(requestSpecification).cookie("token",token)
                    .when().body(payloadManager.fullUpdatePayloadAsString()).put();

            validatableResponse= response.then().log().all();
            validatableResponse.statusCode(200);
            Booking booking= payloadManager.getResponseFromJSON(response.asString());
            assertActions.verifyStringKeyNotNull(booking.getFirstname());
            assertActions.verifyStringKey(booking.getFirstname(),"Sujata");




        }

        @Test(groups = "qa", priority = 4)
        @Owner("Sujata")
        @Description("TC#INT1 Step 4- Delete the booking by ID")
        public void testdeleteBookingByID(ITestContext iTestContext)
        {
            Integer bookingID =(Integer) iTestContext.getAttribute("bookingid");
            String token=(String) iTestContext.getAttribute("token");
//            if(token.equalsIgnoreCase(null))
//            {
//                token=getToken();
//            }

            String basePathDELETE = APIConstants.CREATE_UPDATE_BOOKING_URL+"/"+bookingID;

            requestSpecification.basePath(basePathDELETE).cookie("token",token);
                    validatableResponse=RestAssured.given().spec(requestSpecification).when().delete().then().log().all();

            validatableResponse.statusCode(201);
        }



    }


