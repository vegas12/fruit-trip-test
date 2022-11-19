package com.vegas.fruit_ride.Entity;

public class Fruit {
    public enum Type {
        CHERRY,
        PEACH,
        PEAR
    }

    private int price;
    private Type type;

    public Fruit(Type type, int price) {
        this.type = type;
        this.price = price;
    }

    public Type getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }
}