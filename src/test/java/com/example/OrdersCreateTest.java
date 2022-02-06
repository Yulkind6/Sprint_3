package com.example;

import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

@RunWith(Parameterized.class)
public class OrdersCreateTest {

    private List<ScooterColor> color;
    private OrderClient orderClient;

    public OrdersCreateTest(List<ScooterColor> color) {
        this.color = color;
    }

    @After
    public void tearDown(int trackNumber) {
        orderClient.cancel(trackNumber);
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {List.of(ScooterColor.BLACK, ScooterColor.GREY)},
                {null}
        };
    }

    @Test
    public void orderCanBeCreated() {
        OrderClient orderClient = new OrderClient();

        Order order = Order.getOrder().setColor(color);

        ValidatableResponse response = orderClient.create(order);
        int statusCode = response.extract().statusCode();
        Integer isOrderCreated = response.extract().path("track");

        assertThat("Status code is incorrect", statusCode, equalTo(201));
        assertThat("Order is not created", isOrderCreated, notNullValue());

    }
}