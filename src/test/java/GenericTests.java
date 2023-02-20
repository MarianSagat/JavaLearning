import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class GenericTests {
    @Test
    public void dummyTest() {
        System.out.println("dummy test");
    }

    @Test
    public void simpleGeneric() {
        //NOTE: type specification after new is redundant, use IntelIj autocompletition with new
        GenericType<String> stringType = new GenericType<>("stringText");
        System.out.println("object type name is: " + stringType.getObjTypeName());

        //generic for basic primitive types is not possible diff from C++
        //GenericType<int> integerType = new GenericType<>(123);

        //123 AUTOBOXING into Integer
        GenericType<Integer> integerType = new GenericType<>(123);
        System.out.println("object type name is: " + integerType.getObjTypeName());

        GenericType<Double> doubleType = new GenericType<>(1.234);
        System.out.println("object type name is: " + doubleType.getObjTypeName());

    }

    @Test
    public void boundedGenericTest() {
        BoundedGeneric<Double> doubles = new BoundedGeneric<>(new Double[]{1.2, 2.2, 3.3, 3.4, 5.5});
        BoundedGeneric<Integer> ints = new BoundedGeneric<>(new Integer[]{1, 2, 3, 4, 5});

        Assert.assertEquals(doubles.calculateAverage(), 3.12);
        Assert.assertEquals(ints.calculateAverage(), 3);
    }

    @Test
    public void wildCardArgumentTest() {
        WildCardArgument<Double> ob1 = new WildCardArgument<>(2.1);
        WildCardArgument<Float> ob2 = new WildCardArgument<>(2.1f);
        WildCardArgument<Float> ob3 = new WildCardArgument<>(2.3f);

        Assert.assertFalse(ob1.isTheSameType(ob2), "not same");
        Assert.assertTrue(ob3.isTheSameType(ob2), "same");

        // here compiler know exact types
        //  error: incompatible types: WildCardArgument<Float> cannot be converted to WildCardArgument<Double>
        //ob1 = ob2;
        //but still getClass().getTypeName are same for both ob1,ob2
    }

    @Test
    public void instanceOfTest() {
        WildCardArgument<Double> ob1 = new WildCardArgument<>(2.1);
        WildCardArgument<Float> ob2 = new WildCardArgument<>(2.1f);

        Assert.assertTrue(ob1 instanceof WildCardArgument);
        Assert.assertTrue(ob2 instanceof WildCardArgument);

    }

    @Test
    public void genericMethodTest() {
        Assert.assertTrue(GenericMethod.inInside(Integer.valueOf(3), new Integer[]{7, 8, 3}));
        Assert.assertFalse(GenericMethod.inInside(Integer.valueOf(3), new Integer[]{7, 8, 33}));

        Assert.assertTrue(GenericMethod.inInside("dummy string", new Integer[]{7, 8, 3}));

        //WHY? this can compile?
        //final class can not be extended, so still compiler doesnt complain about Integer here ...
        //V parameter has to be the same as T or extended from it, then why????
        //ANSWER: because of missing generic argument in interface Comparable<T>, but then what it means?
        Assert.assertTrue(GenericMethod.inInside(Double.valueOf(3.1), new Integer[]{7, 8, 3}));

    }

    @Test
    public void genericMethodTest2() {
        Assert.assertTrue(GenericMethod.inInsideVer2(Integer.valueOf(3), new Integer[]{7, 8, 3}));
        Assert.assertFalse(GenericMethod.inInsideVer2(Integer.valueOf(3), new Integer[]{7, 8, 33}));

        //same as above, but this time compiler complains
        /*Assert.assertTrue(GenericMethod.inInsideVer2("dummy string", new Integer[]{7, 8, 3}))*/;

        //same as above, but this time compiler complains
        /*Assert.assertTrue(GenericMethod.inInsideVer2(Double.valueOf(3.1), new Integer[]{7, 8, 3}));*/

    }



    @Test
    public void minMaxTest() {
        MinMax<Integer> integerMinMax = new MinMax<>(new Integer[]{1, 2, 3, 4, 8, 9, 10, -2, 11});
        System.out.println("min je "+ integerMinMax.min());
        System.out.println("max je "+ integerMinMax.max());
    }
    interface Printer{
        void print(String msg);
    }
    @Test
    public void genericInheritanceTest() {

        //lamda in java is anonymous function, we can name it only by using functional interface? :D
        Printer p = (String msg)->{System.out.println(msg);};

        GenericBase<String> baseObject = new GenericBase<>("base");
        GenericDerived<String> derived  = new GenericDerived<>("derived");
        derived.getValue();
        //SEE
        //output src/test/testResources/instanceOf.PNG

        if(baseObject instanceof GenericBase<String>)
            p.print("baseObject is GenericBase<String>");

        if(baseObject instanceof GenericBase<?>)
            p.print("baseObject is GenericBase<?>");

        //NOTE: PRECO TO FUNGUJE? PODLA KNIHY NEMA, JAVA 16
        if(derived instanceof GenericBase<String>)
            p.print("derivedObject is GenericBase<String>");

        if(derived instanceof GenericBase<?>)
            p.print("derivedObject is GenericBase<?>");

        //NOTE: PRECO TO FUNGUJE? PODLA webu NEMA (sice stareho)
        List<String> list = new ArrayList<>();
        if( list instanceof List<String>)
            p.print("list has type List<String>");

    }

    @Test
    public void emptyTest() {

    }

    @Test
    public void testFromBranchForIntellijGit() {

    }

    @Test
    public void intellijGitPokus2() {
        GenericBase<String> baseObject = new GenericBase<>("Base new name.");
        String val = baseObject.getValue();
    }

    @Test
    public void anotherEmpty() {

    }
}
