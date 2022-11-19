import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class CheapestStandFinder {
    // körsbär, persika, päron
    private static int[][] stands = {{2,7,9},{5,9,5},{5,1,7},{9,5,4},{3,3,3}};

    public static void main(String[] args) {
        int min = Arrays.stream(stands[0]).sum();
        int prevmin = min;
        int i = 0;
        int firstCheapestAtIndex = 0;

        for (int[] stand : stands) {
            int pear = stand[stand.length-1];

            int index = 0;
            while (index < stand.length-1) {
                min = Math.min(stand[index] + pear, min);
                index++;
            }

            if (min < prevmin) {
                firstCheapestAtIndex = i;
            }

            prevmin = min;
            i++;
        }

        System.out.println(ordinal(firstCheapestAtIndex + 1));
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