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
        BoundedGeneric<Integer> ints = new BoundedGeneric<>(new Integer[]{1,2,3,4,5});

        Assert.assertEquals(doubles.calculateAverage(),3.12);
        Assert.assertEquals(ints.calculateAverage(),3);
    }

    @Test
    public  void WildCardArgumentTest() {
        WildCardArgument<Double> ob1 = new WildCardArgument<>(2.1);
        WildCardArgument<Float> ob2 = new WildCardArgument<>(2.1f);
        WildCardArgument<Float> ob3 = new WildCardArgument<>(2.3f);

        Assert.assertFalse(ob1.isTheSameType(ob2), "not same");
        Assert.assertTrue(ob3.isTheSameType(ob2),"same");

        // here compiler know exact types
        //  error: incompatible types: WildCardArgument<Float> cannot be converted to WildCardArgument<Double>
        //ob1 = ob2;
        //but still getClass().getTypeName are same for both ob1,ob2
    }

}
