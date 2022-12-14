package com.vegas.fruit_ride.Repository;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.*;
import static java.util.Map.entry;
import com.vegas.fruit_ride.Entity.Stand;
import com.vegas.fruit_ride.Entity.Fruit;
import java.util.stream.Collectors;

public class StandRepositorySingleTon {
    private static StandRepositorySingleTon single_instance = null;

    // Since I am not using a file or some SQLite I have no way of persisting data
    // so this class is singleton and gets mutated during runtime
    private List<Map<String, Integer>> data;

    public StandRepositorySingleTon() {
        this.data = Arrays.asList(
            Map.ofEntries(
                // Fruit name, price
                entry("CHERRY", 3),
                entry("PEAR", 1)
            ),
            Map.ofEntries(
                entry("CHERRY", 3),
                entry("PEACH", 9)
            ),
            Map.ofEntries(
                entry("PEACH", 7),
                entry("PEAR", 7)
            ),
            Map.ofEntries(
                entry("CHERRY", 4),
                entry("PEAR", 5)
            )
        );
    }

    public static StandRepositorySingleTon getInstance()
    {
        if (single_instance == null)
            single_instance = new StandRepositorySingleTon();

        return single_instance;
    }

    public List<Stand> getStands() {
        List<Stand> stands = new ArrayList<>();

        for (Map<String, Integer> stand : data) {
            List<Fruit> fruits =
                    stand.entrySet()
                            .stream()
                            .map(e -> new Fruit(Fruit.Type.valueOf(e.getKey()), e.getValue()))
                            .collect(Collectors.toCollection(ArrayList::new));
            stands.add(
                    new Stand(fruits)
            );
        }

        return stands;
    }

    public void updateProductsForStandAtIndex(int index, List<Fruit> fruits) throws RuntimeException {
        if (index > data.size()) {
            throw new RuntimeException("No Stand at index " + index);
        }

        this.data.set(index, fruits.stream().collect(Collectors.toMap(p -> p.getType().name(), p -> p.getPrice())));
    }
}