import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.stream.Stream;

/**
 * CHECKED EXCEPTIONS HAS TO BE HANDLED OR PROPAGATED VIA THROWS
 */
public class CheckedExceptionsTests
{
    BooleanSupplier consumer = () ->
    {
        List<FunctionalIterfacesTests.Employer> employers = List.of(new FunctionalIterfacesTests.Employer("jozo", 0),
                new FunctionalIterfacesTests.Employer("fero", 1), new FunctionalIterfacesTests.Employer("", -1));

        return false;
        //ERROR: unreported exception InterruptedException; must be caught or declared to be thrown
        //employers.get(0).compareNamesWithInterruptedException(employers.get(1));
    };

    //SOLUTION: 1.) try catch 2.) propagate exception
    //2.) - i dont want to handle exception inside lamba,
    //propagate exception and make caller responsible for handling exception

    //SOLUTION 2
    @FunctionalInterface
    public interface BooleanSupplierWithThrow
    {
        boolean getAsBoolean() throws InterruptedException;
    }

    BooleanSupplierWithThrow consumerSolution2 = () ->
    {
        List<FunctionalIterfacesTests.Employer> employers = List.of(new FunctionalIterfacesTests.Employer("jozo", 0),
                new FunctionalIterfacesTests.Employer("fero", 1), new FunctionalIterfacesTests.Employer("", -1));

        return employers.get(0).compareNamesWithInterruptedException(employers.get(1));
    };

    @Test
    public void callerHandles_InterruptedException_test()
    {
        System.out.println(
                Stream.of("1", "2", "3", "4")
                        .map(entry -> entry + "a")
                        .reduce("", (a, b) ->
                                {
                                    return a + " " + b;
                                }
                        ));

        ;
        try
        {
            Assert.assertFalse(consumerSolution2.getAsBoolean());
        }
        catch(InterruptedException interruptedException)
        {
            interruptedException.printStackTrace();
        }
    }
}
