import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TypeErasureTests
{
    @Test
    public void typeErasure()
    {
        /*****
         * (1) generics doesnt exist after compilation into java bite code
         * -> generic parameters/types are converted to Objects or LeftBound objects?
         * applies for all generics
         *
         * (2) primitive types cannot be generic parameters -> because of type erasure -> cannot convert to objects (not exactly true -autoboxing)
         *
         * (3) inside of parametrized class cannot call new T(), because type erasure, workaround
         * :Class<T> clss = ... , clss.getType
         */


        List<String> list = List.of("1", "2", "3");
        System.out.println(list instanceof List<String>);
        
        List<? extends Object> list_2 = list;
        // TYPE ERASURE -> CANNOT BE SAFELY CAST TO LIST<STRING>
        //System.out.println(list_2 instanceof List<String>);   COMPILE ERROR
        list_2 = List.of(Integer.valueOf(2));
        //System.out.println(list_2 instanceof List<String>);   COMPILE ERROR
        
        System.out.println(list_2 instanceof List<?>); // COMPILE OK
    }

    // type erasure --> void print(List integers) has the same signature as method below
    // : void print(List doubles)
    public void print(List<Integer> integers)
    {

    }

    //COMPILE ERROR: name clash, methods have same singnature afte type erasure
    //it has generic interface inside itself
    /*public void print(List<Double> doubles)
    {

    }*/
}
