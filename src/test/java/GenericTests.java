import org.testng.Assert;
import org.testng.annotations.Test;

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
    public void BoundedGenericTest() {
        BoundedGeneric<Double> doubles = new BoundedGeneric<>(new Double[]{1.2, 2.2, 3.3, 3.4, 5.5});
        BoundedGeneric<Integer> ints = new BoundedGeneric<>(new Integer[]{1, 2, 3, 4, 5});

        Assert.assertEquals(doubles.calculateAverage(), 3.12);
        Assert.assertEquals(ints.calculateAverage(), 3);
    }

    @Test
    public void WildCardArgumentTest() {
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
    public void InstanceOfTest() {
        WildCardArgument<Double> ob1 = new WildCardArgument<>(2.1);
        WildCardArgument<Float> ob2 = new WildCardArgument<>(2.1f);

        Assert.assertTrue(ob1 instanceof WildCardArgument);
        Assert.assertTrue(ob2 instanceof WildCardArgument);

    }

    @Test
    public void GenericMethodTest() {
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
    public void GenericMethodTest2() {
        Assert.assertTrue(GenericMethod.inInsideVer2(Integer.valueOf(3), new Integer[]{7, 8, 3}));
        Assert.assertFalse(GenericMethod.inInsideVer2(Integer.valueOf(3), new Integer[]{7, 8, 33}));

        //same as above, but this time compiler complains
        /*Assert.assertTrue(GenericMethod.inInsideVer2("dummy string", new Integer[]{7, 8, 3}))*/;

        //same as above, but this time compiler complains
        /*Assert.assertTrue(GenericMethod.inInsideVer2(Double.valueOf(3.1), new Integer[]{7, 8, 3}));*/

    }

}
