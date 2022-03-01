// ложится тест duplicateCourierCannotBeCreated, если бы мог подсказать, что с ним не так, была бы очень благодарна :)
package com.example;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

        assertEquals("Status code is incorrect", 201,  statusCode);
        assertTrue("Courier is not created", isCourierCreated);
    }

    @Test
    @DisplayName("Duplicate courier cannot be created")
    public void duplicateCourierCannotBeCreated() {
        courierClient.create(courier);

        ValidatableResponse response = courierClient.create(courier);

        int statusCode = response.extract().statusCode();
        String errorMessage = response.extract().path("message");

        assertEquals("Courier is not created", 409, statusCode);
        assertEquals("Courier is not created", "Этот логин уже используется. Попробуйте другой.", errorMessage);
    }
}
