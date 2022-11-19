package com.vegas.fruit_ride;

import com.vegas.fruit_ride.Repository.*;
import com.vegas.fruit_ride.Entity.*;
import com.vegas.fruit_ride.UseCase.CheapestStandFinder;

public class Application {
    public static void main(String[] args) {
        try {
            CheapestStandFinder finder = new CheapestStandFinder();

            finder.run(2); // Pelle and Kajsa rides
        } catch (RuntimeException e) {
            System.out.println("An unexpected error occured");
        }
    }
}