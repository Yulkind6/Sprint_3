/// не поняла, как прикрепляется отчет:) на всякий случай ссылка: http://192.168.0.10:52509/index.html# но один тест все равно падает:( 
package com.example;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

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
        return new Object[][]{
                {Courier.getWithLoginOnly(), 400, "Error"},
                {Courier.getWithPasswordOnly(), 400, "Error"},
                {Courier.getWithFirstNameOnly(), 400, "Error"}
        };
    }

    @Test
    @DisplayName("Courier cannot be created with login only")
    public void invalidRequestIsNotAllowed1() {
        Courier courier = Courier.getWithLoginOnly();

        ValidatableResponse response = new CourierClient().create(courier);

        int statusCode = response.extract().statusCode();
        String message = response.extract().path("message");

        assertEquals("Status code is incorrect", 400, statusCode);
    }

    @Test
    @DisplayName("Courier cannot be created with password only")
    public void invalidRequestIsNotAllowed2() {
        Courier courier = Courier.getWithPasswordOnly();

        ValidatableResponse response = new CourierClient().create(courier);

        int statusCode = response.extract().statusCode();
        String message = response.extract().path("message");

        assertEquals("Status code is incorrect", 400, statusCode);
    }
    @Test
    @DisplayName("Courier cannot be created with first name only")
    public void invalidRequestIsNotAllowed3() {
        Courier courier = Courier.getWithFirstNameOnly();

        ValidatableResponse response = new CourierClient().create(courier);

        int statusCode = response.extract().statusCode();
        String message = response.extract().path("message");

        assertEquals("Status code is incorrect", 400, statusCode);
    }
}

