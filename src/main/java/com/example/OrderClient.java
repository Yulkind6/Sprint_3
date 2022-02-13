package com.example;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class OrderClient extends RestAssuredClient {
    private static final String ORDERS_PATH = "/api/v1/orders";


    @Step("Create order")
    public ValidatableResponse create(Order order) {
        return given()
                .spec(getBaseSpec())
                .body(order)
                .when()
                .post(ORDERS_PATH)
                .then();
    }

    @Step("Cancel order")
    public ValidatableResponse cancel(int track) {
        return given()
                .spec(getBaseSpec())
                .body(track)
                .when()
                .post(ORDERS_PATH + "/cancel")
                .then();
    }
}
