package com.example;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import java.util.List;

import static io.restassured.RestAssured.given;

public class OrderClient extends RestAssuredClient {
    private static final String ORDERS_PATH = "/api/v1/orders";

    @Step
    public ValidatableResponse create(Order order) {
        return given()
                .spec(getBaseSpec())
                .body(order)
                .when()
                .post(ORDERS_PATH)
                .then();
    }

    @Step
    public ValidatableResponse cancel(int track) {
        return given()
                .spec(getBaseSpec())
                .body(track)
                .when()
                .post(ORDERS_PATH + "/cancel")
                .then();
    }
    @Step
    public ValidatableResponse getListOrders(List<Object> orders) {
        return given()
                .spec(getBaseSpec())
                .when()
                .get(ORDERS_PATH+"?courierId=")
                .then();
    }
}
