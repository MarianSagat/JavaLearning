import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class FunctionalIterfacesTests {

    static class Employer {
        Employer(String name,Integer id) {
            this.name = name;
            this.id = id;
        }
        //DEFAULT MODIFIER IS PUBLIC INSIDE PACKAGE, OUTSIDE PACKAGE IS PRIVATE
        String name;
        Integer id;
    }
    //Function is C++ counterpart for std::function
    @Test
    public void functionTest() {
        Function<Employer,Integer> getId = (employer)-> employer.id;
        System.out.println(getId.apply(new Employer("Majo",666)));
    }

    @Test
    public void consumerTest() {
        Consumer<Employer> printName = employer -> {System.out.println(employer.name);};
        printName.accept(new Employer("ferko",665));
    }

    @Test
    public void predicateTest() {
        Predicate<Employer> isNewEmployer = employer -> {return employer.id.compareTo(665) >0;};
        Assert.assertTrue(isNewEmployer.test(new Employer("Majo",666)));
        Assert.assertFalse(isNewEmployer.test(new Employer("Majo",665)));
    }
}
