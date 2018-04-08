import org.junit.*;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

public class EgorTest extends Assert{

    @Rule
    public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();

    @Test
    public void testEgor() {

            systemInMock.provideLines("4", "4578", "5", "1256", "4323", "1897", "3244", "5678",
                                           "1256", "2", "4578", "1897",
                                           "4323", "4", "5678", "6547", "9766", "9543",
                                           "9766", "1", "3624");
            long st = System.nanoTime();
            Egor.run();
            long en = System.nanoTime();
            System.out.println("\ncold start time " + ((double) (en - st) / 1000000000) + " sec");
    }
}
