import Repository.*;
import Entity.*;
import UseCase.CheapestStandFinder;

public class Application {
    public static void main(String[] args) {
        try {
            CheapestStandFinder finder = new CheapestStandFinder();

            finder.run(2, Fruit.Type.PEACH); // Pelle and Kajsa rides
            System.out.println("-----------");
            finder.run(1, Fruit.Type.PEACH); // Their friend rides
        } catch (RuntimeException e) {
            System.out.println("An unexpected error occured");
        }
    }
}