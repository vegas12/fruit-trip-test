package com.vegas.fruit_ride.UseCase;

import com.vegas.fruit_ride.Repository.*;
import com.vegas.fruit_ride.Entity.*;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
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

    public void run(int amountOfFruitsToBuy) throws RuntimeException {
        run(amountOfFruitsToBuy, new HashSet<>());
    }

    public void run(int amountOfFruitsToBuy, Set<Fruit.Type> requiredTypes) throws RuntimeException {
        if (amountOfFruitsToBuy < requiredTypes.size()) {
            throw new RuntimeException("You can not require more specific types than the amount of fruits you want to buy.");
        }

        List<Stand> stands = repository.getStands();
        int min = Integer.MAX_VALUE;
        int prevmin = Integer.MAX_VALUE;
        int currentStand = 0;
        int cheapestAtIndex = 0;
        List<Fruit> boughtFruits = new ArrayList<Fruit>();

        for (Stand stand : stands) {
            List<Fruit.Type> fruitTypes = stand.getFruits().stream().map(e -> e.getType()).collect(Collectors.toList());
            boolean hasRequiredFruits = requiredTypes.isEmpty() ?
                stand.getFruits().size() > 0 :
                fruitTypes.containsAll(requiredTypes);

            if (!hasRequiredFruits) {
                currentStand++;
                continue;
            }

            List<Fruit> fruits = stand.getFruits();
            List<Fruit> prospectFruits = new ArrayList<>();

            if (requiredTypes.isEmpty()) {
                prospectFruits = fruits
                    .stream()
                    .sorted(Comparator.comparingInt(fruit -> fruit.getPrice()))
                    .limit(amountOfFruitsToBuy)
                    .collect(Collectors.toList());
            } else {
                prospectFruits = fruits
                    .stream()
                    .filter(fruit -> requiredTypes.contains(fruit.getType()))
                    .collect(Collectors.toList());
                prospectFruits.addAll(
                    fruits
                        .stream()
                        .filter(fruit -> !requiredTypes.contains(fruit.getType()))
                        .sorted(Comparator.comparingInt(fruit -> fruit.getPrice()))
                        .limit(amountOfFruitsToBuy - prospectFruits.size())
                        .collect(Collectors.toList())
                );
            }

            int sumOfFruits = prospectFruits
                .stream()
                .mapToInt(Fruit::getPrice)
                .sum();

            min = Math.min(sumOfFruits, min);

            if (min < prevmin) {
                cheapestAtIndex = currentStand;
                boughtFruits.clear();
                boughtFruits.addAll(prospectFruits);
                prevmin = min;
            }

            prevmin = min;
            currentStand++;
        }

        if (boughtFruits.isEmpty()) {
            System.out.println("Could not find any fruit to buy");
            return;
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