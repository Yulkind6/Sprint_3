package com.example;

import org.apache.commons.lang3.RandomStringUtils;

public class CourierGenerator {
    private static final int DATA_SIZE = 10;

    public static Courier getRandom() {
        final String login = RandomStringUtils.randomAlphabetic(DATA_SIZE);
        final String password = RandomStringUtils.randomAlphabetic(DATA_SIZE);
        final String firstName = RandomStringUtils.randomAlphabetic(DATA_SIZE);
        return new Courier(login, password, firstName);
    }
}
