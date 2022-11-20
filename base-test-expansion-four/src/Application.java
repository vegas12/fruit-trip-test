package com.vegas.fruit_ride;

import com.vegas.fruit_ride.Repository.*;
import com.vegas.fruit_ride.Entity.*;
import com.vegas.fruit_ride.UseCase.CheapestStandFinder;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Application {
    public static void main(String[] args) {
        try {
            CheapestStandFinder finder = new CheapestStandFinder();

            System.out.println("----- Pelle and Kajsa rides ------");
            finder.run(2, new HashSet<>(Arrays.asList(Fruit.Type.PEAR)));
            System.out.println("----- The friend rides ------");
            finder.run(1);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}