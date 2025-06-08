package org.testing.tests.e2e_integration;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.testing.base.BaseTest;
import org.testing.endpoint.APIConstants;
import org.testing.pojos.request.Booking;
import org.testing.pojos.response.BookingResponse;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class TestIntegrationFlow2 extends BaseTest {
    // Create Booking -> Delete it -> Verify
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
    @Description("TC#INT2 - Step 2. Delete the Booking")
    public void testDeleteBooking(ITestContext iTextContext)
    {
        Integer bookingid = (Integer) iTextContext.getAttribute("bookingid");
        System.out.println(bookingid);
        String basePathverifyDELETE = APIConstants.CREATE_UPDATE_BOOKING_URL+"/" + bookingid;
        System.out.println(basePathverifyDELETE);

        requestSpecification.basePath(basePathverifyDELETE);
        response= RestAssured.given(requestSpecification).when().delete();
        //validatableResponse.statusCode(201);

    }

    @Test(groups = "qa", priority = 3)
    @Owner("Sujata")
    @Description("TC#INT2 - Step 3. Verify the Booking")
    public void verifyBooking(ITestContext iTextContext)
    {
        Integer bookingid = (Integer) iTextContext.getAttribute("bookingid");
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

    }


