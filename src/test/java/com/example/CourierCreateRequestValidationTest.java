package com.example;

import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class CourierCreateRequestValidationTest {

    private final Courier courier;
    private final int expectedStatus;
    private final String expectedErrorMessage;

    public CourierCreateRequestValidationTest(Courier courier, int expectedStatus, String expectedErrorMessage) {
        this.courier = courier;
        this.expectedStatus = expectedStatus;
        this.expectedErrorMessage = expectedErrorMessage;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][] {
                {Courier.getWithLoginOnly(), 400, "Error"},
                {Courier.getWithPasswordOnly(), 400, "Error"},
                {Courier.getWithPasswordAndLogin(), 201, null} //почистить за собой
        };
    }

    @Test
    public void invalidRequestIsNotAllowed() {
        Courier courier = Courier.getWithLoginOnly();

        ValidatableResponse response = new CourierClient().create(courier);

//        asserts




        String message = response.extract().path("message");

        assert message == null;
    }
}

