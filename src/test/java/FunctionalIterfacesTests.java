import org.openqa.selenium.InvalidArgumentException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.function.*;

public class FunctionalIterfacesTests
{

    //this is ok, methods can throw
    Supplier<Employer> employerConsumer = () ->
            List.of(new Employer("jozo", 0), new Employer("fero", 1))
                    .stream()
                    .filter(e -> e.name.contains("jo"))
                    .toList()
                    .get(0);

    //this also ok, custom method throw InvalidArgumentException
    BooleanSupplier employerConsumer2 = () ->
    {
        List<Employer> employers = List.of(new Employer("jozo", 0),
                new Employer("fero", 1), new Employer("", -1));

        return employers.get(0).compareNamesWithInvalidArgumentException(employers.get(1));
    };

    //but this is NOT OK, why?
    //error: unreported exception InterruptedException; must be caught or declared to be thrown
    //BECAUSE IT IS CHECK EXCEPTION AND THIS HAVE TO BE HANDLED OR METHOD HAS TO DECLARE EXCEPTION
    BooleanSupplier employerConsumer3 = () ->
    {
        List<Employer> employers = List.of(new Employer("jozo", 0),
                new Employer("fero", 1), new Employer("", -1));

        return false;
        //ERROR:
        //employers.get(0).compareNamesWithInterruptedException(employers.get(1));
    };

    static class Employer
    {
        Employer(String name, Integer id)
        {
            this.name = name;
            this.id = id;
        }

        public boolean compareNamesWithInvalidArgumentException(Employer other) throws InvalidArgumentException
        {
            if(this.name.equals("") || other.name.equals(""))
            {
                throw new InvalidArgumentException("Not valid name");
            }

            return this.name.equals(other.name);
        }

        public boolean compareNamesWithInterruptedException(Employer other) throws InterruptedException
        {
            if(this.name.equals("") || other.name.equals(""))
            {
                throw new InterruptedException("Not valid name");
            }

            return this.name.equals(other.name);
        }

        //DEFAULT MODIFIER IS PUBLIC INSIDE PACKAGE, OUTSIDE PACKAGE IS PRIVATE
        String name;
        Integer id;
    }

    //Function is C++ counterpart for std::function
    @Test
    public void functionTest()
    {
        Function<Employer, Integer> getId = (employer) -> employer.id;
        System.out.println(getId.apply(new Employer("Majo", 666)));
    }

    @Test
    public void consumerTest()
    {
        Consumer<Employer> printName = employer -> {
            System.out.println(employer.name);
        };
        printName.accept(new Employer("ferko", 665));
    }

    @Test
    public void predicateTest()
    {
        Predicate<Employer> isNewEmployer = employer -> {
            return employer.id.compareTo(665) > 0;
        };
        Assert.assertTrue(isNewEmployer.test(new Employer("Majo", 666)));
        Assert.assertFalse(isNewEmployer.test(new Employer("Majo", 665)));
    }
}
