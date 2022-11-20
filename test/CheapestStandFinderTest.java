import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import com.vegas.fruit_ride.UseCase.CheapestStandFinder;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;
import java.util.HashSet;
import com.vegas.fruit_ride.Entity.*;
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
}