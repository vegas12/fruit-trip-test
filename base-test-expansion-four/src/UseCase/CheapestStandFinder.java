package com.vegas.fruit_ride.UseCase;

import com.vegas.fruit_ride.Repository.*;
import com.vegas.fruit_ride.Entity.*;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.Comparator;
import java.util.Collections;
import java.util.function.Predicate;

public class CheapestStandFinder {
    private StandRepositorySingleTon repository;

    public CheapestStandFinder() {
        repository = StandRepositorySingleTon.getInstance();
    }

    public void run(int amountOfFruitsToBuy, List<Fruit.Type> requiredTypes) throws RuntimeException {
        if (amountOfFruitsToBuy < requiredTypes.size()) {
            throw new RuntimeException("You cant require more specific types than the amount of fruits you want to buy.");
        }

        List<Stand> stands = repository.getStands();
        List<Stand> standsWithRequiredTypes = requiredTypes.isEmpty() ?
            stands :
            stands
                .stream()
                .filter(stand ->
                    stand.getFruits().stream().map(Fruit::getType).anyMatch(requiredTypes::contains)
                )
                .collect(Collectors.toList());

        int min = standsWithRequiredTypes.get(0).getFruits()
            .stream()
            .mapToInt(Fruit::getPrice)
            .sum();
        int prevmin = min;
        int currentStand = 0;
        int cheapestAtIndex = 0;
        List<Fruit> boughtFruits = stands
                .get(0)
                .getFruits()
                .stream()
                .sorted(Comparator.comparingInt(fruit -> fruit.getPrice()))
                .limit(amountOfFruitsToBuy)
                .collect(Collectors.toList());

        for (Stand stand : stands) {
            List<Fruit> requiredFruits = stand
                .getFruits()
                .stream()
                .filter(fruit -> requiredTypes.contains(fruit.getType()))
                .collect(Collectors.toList());

            if (requiredFruits.size() == 0) {
                currentStand++;
                continue;
            }

            List<Fruit> cheapestNonRequiredFruits = stand
                .getFruits()
                .stream()
                .filter(fruit -> !requiredTypes.contains(fruit.getType()))
                .sorted(Comparator.comparingInt(fruit -> fruit.getPrice()))
                .limit(amountOfFruitsToBuy-1) // One amount is spent on the required fruit, hence -1.
                .collect(Collectors.toList());

            int sumOfNonRequiredFruits = cheapestNonRequiredFruits
                .stream()
                .mapToInt(Fruit::getPrice)
                .sum();

            min = Math.min(sumOfNonRequiredFruits + requiredFruits.get(0).getPrice(), min);

            if (min < prevmin) {
                cheapestAtIndex = currentStand;
                boughtFruits.clear();
                boughtFruits.addAll(Arrays.asList(requiredFruits.get(0)));
                boughtFruits.addAll(cheapestNonRequiredFruits);
                prevmin = min;
            }

            prevmin = min;
            currentStand++;
        }

        System.out.println("Stand: " + ordinal(cheapestAtIndex + 1));
        System.out.println("Total price: " + min);
        System.out.println("Selected amongst: " + stands.get(cheapestAtIndex).getFruits().size() + " fruits");

        for (Fruit boughtFruit : boughtFruits) {
            System.out.println("Bought: " + boughtFruit.getType() + " for " + boughtFruit.getPrice());
        }

        List<Fruit> fruits = stands.get(cheapestAtIndex).getFruits();
        fruits.removeIf((Fruit fruit) -> boughtFruits.contains(fruit));

        repository.updateProductsForStandAtIndex(
            cheapestAtIndex,
            fruits
        );
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