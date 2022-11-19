package com.vegas.fruit_ride;

import com.vegas.fruit_ride.Repository.*;
import com.vegas.fruit_ride.UseCase.CheapestStandFinder;

public class Application {
    public static void main(String[] args) {
        CheapestStandFinder finder = new CheapestStandFinder(new StandRepository());
        finder.run();
    }
}