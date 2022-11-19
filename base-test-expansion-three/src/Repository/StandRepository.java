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

public class StandRepository {
    public static List<Map<String, Integer>> data = Arrays.asList(
            Map.ofEntries(
                    // Fruit name, price
                    entry("CHERRY", 3),
                    entry("PEACH", 4),
                    entry("PEAR", 7)
            ),
            Map.ofEntries(
                    entry("CHERRY", 3),
                    entry("PEACH", 4)
            ),
            Map.ofEntries(
                    entry("PEACH", 4),
                    entry("PEAR", 7)
            ),
            Map.ofEntries(
                    entry("CHERRY", 3),
                    entry("PEAR", 7)
            )
    );

    public static List<Stand> getStands() {
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
}