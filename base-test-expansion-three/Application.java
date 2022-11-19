import Repository.*;
import UseCase.CheapestStandFinder;

public class Application {
    public static void main(String[] args) {
        CheapestStandFinder finder = new CheapestStandFinder(new StandRepository());
        finder.run();
    }
}