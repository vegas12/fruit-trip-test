package Entity;

import java.util.List;
import Entity.*;

public class Stand {
    private List<Fruit> fruits;

    public Stand(List<Fruit> fruits) {
        this.fruits = fruits;
    }

    public List<Fruit> getFruits() {
        return fruits;
    }
}