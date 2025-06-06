package org.testing.tests.sample;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestIntegrationSample {
    // Create A Booking, Create a Token
    // Verify that Get booking -
    // Update the Booking
    // Delete the Booking

    @Test(groups = "qa", priority = 1)
    @Owner("Sujata")
    @Description("TC#INT1 - Step 1. Verify that the Booking can be Created")
    public void testCreateBooking(){
        Assert.assertTrue(true);
    }

    @Test(groups = "qa",priority =2 )
    @Owner("Sujata")
    @Description("TC#2 Step 2. Verify the Booking by ID")
    public void testVerifyBooking()
    {
        Assert.assertTrue(true);
    }

    @Test(groups="qa", priority = 3)
    @Owner("Sujata")
    @Description("TC#3 Step 3- Verify updated booking By ID")
    public void testUpdatebooking()
    {
        Assert.assertTrue(true);
    }

    @Test(groups = "qa", priority = 4)
    @Owner("Sujata")
    @Description("TC#4 Step 4- Delete the booking by ID")
    public void testdeleteBookingByID()
    {
        Assert.assertTrue(true);
    }
}
