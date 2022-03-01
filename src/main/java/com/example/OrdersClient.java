package com.example;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class OrdersClient extends RestAssuredClient {
    private static final String ORDERS_PATH = "/api/v1/orders";

    @Step("Get the orders list")
    public static ValidatableResponse getAll() {
        return given()
                .spec(getBaseSpec())
                .when()
                .get(ORDERS_PATH + "?courierId=")
                .then();
    }
}
