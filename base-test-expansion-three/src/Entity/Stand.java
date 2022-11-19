package com.vegas.fruit_ride.Entity;

import java.util.List;
import com.vegas.fruit_ride.Entity.*;

public class Stand {
    private List<Fruit> fruits;

    public Stand(List<Fruit> fruits) {
        this.fruits = fruits;
    }

    public List<Fruit> getFruits() {
        return fruits;
    }
}