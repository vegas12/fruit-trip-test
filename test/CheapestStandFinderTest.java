import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import com.vegas.fruit_ride.UseCase.CheapestStandFinder;
import com.vegas.fruit_ride.Entity.*;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;
import java.util.HashSet;
import java.util.Arrays;

public class CheapestStandFinderTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void testUseCasePrintsToSystemOut() {
        CheapestStandFinder finder = new CheapestStandFinder();

        finder.run(2, new HashSet<>(Arrays.asList(Fruit.Type.PEAR)));

        assertEquals("Stand: 1st\n" +
            "Total price: 4\n" +
            "Selected amongst: 2 fruits\n" +
            "Bought: PEAR for 1\n" +
            "Bought: CHERRY for 3", outputStreamCaptor.toString().trim());
    }

    @Test(expected = RuntimeException.class)
    public void testExceptionIsThrownWhenThereIsMoreRequiredTypesThanFruitsToBuy() {
        CheapestStandFinder finder = new CheapestStandFinder();
        finder.run(1, new HashSet<>(Arrays.asList(Fruit.Type.PEAR, Fruit.Type.PEACH)));
    }

    @Test(expected = RuntimeException.class)
    public void testExceptionIsThrownWhenThereIsMoreRequiredTypesThanFruitsToBuy() {
        CheapestStandFinder finder = new CheapestStandFinder();
        finder.run(1, new HashSet<>(Arrays.asList(Fruit.Type.PEAR, Fruit.Type.PEACH)));
    }


}