package com.example;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;

public class OrdersTest {
    @Test
    @DisplayName("Get the order list")
    public void getOrdersReturnTest () {
        OrdersClient ordersClient = new OrdersClient();

        ValidatableResponse response = OrdersClient.getAll();

        List<Object> orders = response.extract().jsonPath().getList("orders");
        assertNotNull("Order list is empty", orders);
    }
}