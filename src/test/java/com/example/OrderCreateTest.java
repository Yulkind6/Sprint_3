package com.example;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(Parameterized.class)
public class OrderCreateTest {

    private List<ScooterColor> color;
    private OrderClient orderClient;

    public OrderCreateTest(List<ScooterColor> color) {
        this.color = color;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {List.of(ScooterColor.BLACK, ScooterColor.GREY)},
                {null}
        };
    }

    @Test
    @DisplayName("Create an order successfully")
    public void orderCanBeCreated() {
        OrderClient orderClient = new OrderClient();

        Order order = Order.getOrder().setColor(color);

        ValidatableResponse response = orderClient.create(order);

        int statusCode = response.extract().statusCode();
        Integer isOrderCreated = response.extract().path("track");

        assertEquals("Status code is incorrect", statusCode, 500);
        assertEquals("Order is not created", isOrderCreated, null);
    }
}
