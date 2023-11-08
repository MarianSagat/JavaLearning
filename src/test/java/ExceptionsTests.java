import org.junit.jupiter.api.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NotFoundException;

public class ExceptionsTests
{
    /**
     * to be able to hit breakpoint only for base class NotFoundException
     * except it's subClass NoSuchElementException see picture catchNotFoundExceptionButNotNoSuchElementMethod.png
     * DESCRIPTION OF SOLUTION: place condition: !cause.getMessage().contains("no_such_element")
     * which message it has you need to debug/ or look at implemenation of that exception
     * you can also use filter to exclude some specific classes
     */
    @Test
    public void debuggingException_WithOccurenceOfInheretedSubClassException()
    {
        try
        {
            throw new NoSuchElementException("Sub class for NotFoundException.");
        }
        catch(NoSuchElementException noSuchElementException)
        {
            System.out.println(noSuchElementException.toString());
        }
        
        try
        {
            throw new NotFoundException("base class");
        }
        catch(NotFoundException notFoundException)
        {
            System.out.println(notFoundException.toString());
        }
        
        System.out.println("end");
    }
}
