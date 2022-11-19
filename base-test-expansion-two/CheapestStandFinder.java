import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class CheapestStandFinder {
    // körsbär, persika, päron
    private static int[][] stands = {{2,7,9},{5,9,5},{5,1,2},{9,5,4},{3,4,3}};

    enum Fruit {
        CHERRY,
        PEACH,
        PEAR
    }

    public static void main(String[] args) {
        int min = Arrays.stream(stands[0]).sum();
        int prevmin = min;
        int currentStand = 0;
        int firstCheapestAtIndex = 0;
        int boughtFruit = 0;

        for (int[] stand : stands) {
            int pear = stand[stand.length-1];

            int currentFruit = 0;
            while (currentFruit < stand.length-1) {
                min = Math.min(stand[currentFruit] + pear, min);

                if (min < prevmin) {
                    firstCheapestAtIndex = currentStand;
                    boughtFruit = currentFruit;
                    prevmin = min;
                }

                currentFruit++;
            }

            prevmin = min;
            currentStand++;
        }

        System.out.println("Stand: " +ordinal(firstCheapestAtIndex + 1));
        System.out.println("Price: " + min);
        System.out.println("Bought fruits: " + Fruit.values()[boughtFruit].name() + " and a " + Fruit.PEAR);
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