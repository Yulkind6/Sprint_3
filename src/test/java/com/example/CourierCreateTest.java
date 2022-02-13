package com.example;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CourierCreateTest {

    private Courier courier;
    private CourierClient courierClient;
    private int courierId;

    @Before
    public void setUp() {
        courier = CourierGenerator.getRandom();
        courierClient = new CourierClient();
    }

    @After
    public void tearDown() {
        courierClient.delete(courierId);
    }

    @Test
    @DisplayName("Create a courier")
    public void courierCanBeCreatedTest() {
        ValidatableResponse response = courierClient.create(courier);
        courierId = courierClient.login(CourierCredentials.from(courier));

        int statusCode = response.extract().statusCode();
        boolean isCourierCreated = response.extract().path("ok");

        assertTrue("Courier is not created", isCourierCreated);
        assertEquals("Courier is not created", statusCode, 201);

    }

    @Test
    @DisplayName("Duplicate courier cannot be created")
    public void duplicateCourierCannotBeCreated() {
        courierClient.create(courier);

        ValidatableResponse response = courierClient.create(courier);

        String errorMessage = response.extract().path("message");

        assertTrue("Courier is not created", Boolean.parseBoolean(errorMessage));
        assertEquals("Courier is not created", 409, 409);
    }
}