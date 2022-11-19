package UseCase;

import Repository.*;
import Entity.*;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class CheapestStandFinder {
    private StandRepository repository;

    public CheapestStandFinder(StandRepository repository) {
        repository = repository;
    }

    public void run() {
        List<Stand> stands = repository.getStands();
        int sumOfFirstStand = stands.get(0).getFruits()
            .stream()
            .mapToInt(Fruit::getPrice)
            .sum();

        List<Stand> standsWithPears = stands
            .stream()
            .filter(stand ->
                    stand.getFruits().stream().map(Fruit::getType).anyMatch(Fruit.Type.PEAR::equals)
            )
            .collect(Collectors.toList());

        int min = sumOfFirstStand;
        int prevmin = min;
        int currentStand = 0;
        int cheapestAtIndex = 0;
        List<Fruit> boughtFruits = new ArrayList<>();

        for (Stand stand : standsWithPears) {
            Fruit pear = stand
                    .getFruits()
                    .stream()
                    .filter(fruit -> fruit.getType() == Fruit.Type.PEAR)
                    .findFirst()
                    .get();
            List<Fruit> allOtherThanPear = stand
                    .getFruits()
                    .stream()
                    .filter(fruit -> fruit.getType() != Fruit.Type.PEAR)
                    .collect(Collectors.toList());

            for (Fruit fruit : allOtherThanPear) {
                min = Math.min(fruit.getPrice() + pear.getPrice(), min);

                if (min < prevmin) {
                    cheapestAtIndex = currentStand;
                    boughtFruits = Arrays.asList(fruit, pear);
                    prevmin = min;
                }
            }

            prevmin = min;
            currentStand++;
        }

        System.out.println("Stand: " + ordinal(cheapestAtIndex + 1));
        System.out.println("Total price: " + min);

        for (Fruit boughtFruit : boughtFruits) {
            System.out.println("Bought: " + boughtFruit.getType() + " for " + boughtFruit.getPrice());
        }
    }

    private static String ordinal(int i) {
        String[] suffixes = new String[] { "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th" };
        switch (i % 100) {
            case 11:
            case 12:
            case 13:
                return i + "th";
            default:
                return i + suffixes[i % 10];

        }
    }
}