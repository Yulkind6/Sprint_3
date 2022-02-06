package com.example;

import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertTrue;
public class CourierCreateTest {

    private Courier courier;
    private CourierClient courierClient;
    private int courierId;

    @Before
    public void setUp() {
        courier = Courier.getRandom();
        courierClient = new CourierClient();
    }

    @After
    public void tearDown() {
        courierClient.delete(courierId);
    }

    @Test
    public void courierCanBeCreatedTest() {
        ValidatableResponse response = courierClient.create(courier);
        courierId = courierClient.login(CourierCredentials.from(courier));

        int statusCode = response.extract().statusCode();
        boolean isCourierCreated = response.extract().path("ok");

        assertTrue("Courier is not created", isCourierCreated);
        assertThat("Courier ID is incorrect", statusCode, equalTo(201));
        assertThat("Courier ID is not created", courierId, is(not(0)));
    }

    @Test
    public void duplicateCourierCannotBeCreated() {
        courierClient.create(courier);

        ValidatableResponse response = courierClient.create(courier);
//        asserts





        int statusCode = response.extract().statusCode();
        boolean errorMessage = response.extract().path("message");

//    assertTrue("Courier is not created", errorMessage);
//    assertThat("Courier ID is incorrect", courierId, equalTo(400));
//    assertThat("Courier ID is not created", courierId, is(not(0)));
    }
}