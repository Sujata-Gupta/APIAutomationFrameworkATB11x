package org.testing.modules;

import com.github.javafaker.Book;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import org.testing.pojos.request.Auth;
import org.testing.pojos.request.Booking;
import org.testing.pojos.request.Bookingdates;
import org.testing.pojos.response.BookingResponse;
import org.testing.pojos.response.TokenResponse;

public class PayloadManager {
    Gson gson;
    Faker faker;

    public String createPayloadBookingAsString() {
        Booking booking = new Booking();
        booking.setFirstname("Sujata");
        booking.setLastname("Gupta");
        booking.setTotalprice(4562);
        booking.setDepositpaid(true);

        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2024-5-21");
        bookingdates.setCheckout("2024-5-25");

        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Breakfast");

        System.out.println(booking);
        // java to json
        gson = new Gson();
        String jsonStringBooking = gson.toJson(booking);
        return jsonStringBooking;


    }

    public String createPayloadBookingAsStringWrongBody() {
        Booking booking = new Booking();
        booking.setFirstname("Sujata");
        booking.setLastname("!@$!");
        booking.setTotalprice(4562);
        booking.setDepositpaid(false);

        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("024-5-21");
        bookingdates.setCheckout("5024-5-25");

        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("@#!#!");

        System.out.println(booking);
        // java to json
        gson = new Gson();
        String jsonStringBooking = gson.toJson(booking);
        return jsonStringBooking;


    }
    public String createPayloadBookingFakerJS() {

        faker=new Faker();
        Booking booking = new Booking();
        booking.setFirstname(faker.name().firstName());
        booking.setLastname(faker.name().lastName());
        booking.setTotalprice(faker.random().nextInt(1,1000));
        booking.setDepositpaid(faker.random().nextBoolean());

        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("024-5-21");
        bookingdates.setCheckout("5024-5-25");

        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("@#!#!");

        System.out.println(booking);
        // java to json
        gson = new Gson();
        String jsonStringBooking = gson.toJson(booking);
        return jsonStringBooking;


    }
    //json to java

    public BookingResponse bookingResponseJava(String responseString) {

        gson = new Gson();
         BookingResponse bookingresponse = gson.fromJson(responseString, BookingResponse.class);
         return bookingresponse;
    }

    public  String setAuthPayload()
    {
        Auth auth=new Auth();
        auth.setUsername("admin");
        auth.setPassword("password123");
        gson =new Gson();
        String jsonPayloadString =gson.toJson(auth);
        return jsonPayloadString;

    }

    //Deser

    public String getTokenFromJSON(String tokenResponse)
    {
        gson=new Gson();
        TokenResponse tokenResponse1=gson.fromJson(tokenResponse,TokenResponse.class);
        return tokenResponse1.getToken();
    }

    public Booking getResponseFromJSON(String getResponse)
    {
        gson=new Gson();
        Booking booking=gson.fromJson(getResponse,Booking.class);
        return booking;

    }

    public String fullUpdatePayloadAsString()
    {
        Booking booking = new Booking();
        booking.setFirstname("Sujata");
        booking.setLastname("Gupta");
        booking.setTotalprice(4562);
        booking.setDepositpaid(true);

        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2024-5-21");
        bookingdates.setCheckout("2024-5-25");

        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Breakfast");

        System.out.println(booking);
        // java to json
        gson = new Gson();
        String jsonStringBooking = gson.toJson(booking);
        return jsonStringBooking;


    }
}


