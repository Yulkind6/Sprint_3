package com.example;

import java.util.List;

public class Order {

    public String firstName;
    public String lastName;
    public String address;
    public String metroStation;
    public String phone;
    public int rentTime;
    public String deliveryDate;
    public String comment;
    public List<ScooterColor> color;

    public Order(String firstName, String lastName, String address, String metroStation, String phone, int rentTime, String deliveryDate, String comment, List<ScooterColor> color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }
    public static Order getOrder() {
        String firstName = "Анна";
        String lastName = "Иванова";
        String address = "Тверская";
        String metroStation = "Пушкинская";
        String phone = "89181738393";
        int rentTime = 2;
        String deliveryDate = "15.02.2022";
        String comment = "просьба позвонить";
        List<ScooterColor> color = List.of(ScooterColor.BLACK, ScooterColor.GREY);
        return new Order(firstName, lastName, address, metroStation, phone,rentTime, deliveryDate, comment, color);
    }

    public Order setColor(List<ScooterColor> color) {
        this.color = color;
        return this;
    }
}


