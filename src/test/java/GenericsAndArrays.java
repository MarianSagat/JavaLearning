import org.apache.commons.lang3.tuple.Pair;

import java.util.Map;

public class GenericsAndArrays
{
    //IF ? EXTENDS T IS TYPE FOR PAIR.GETKEY(), IF TYPE OF MAP.PUT IS THE SAME: ? EXTENDS T
    //THEN THESE TWO TYPES CAN BE DIFFERENT, IMPLEMENTING T BUT DIFFERENT -> TYPE MISHMASH
    static <T, U> void putIntoMapContentNotCompile(Map<? extends T, ? super U> map, Pair<? extends T, ? extends U> pair)
    {
        //ERROR
        //map.put(pair.getKey(),pair.getValue());
    }

    //? extends T:    ?=T_0,T_1 extends T extends T_-1
    //? super T:    ?=T,T_-1
    static <T, U> void putIntoMap(Map<? super T, ? super U> map, Pair<? extends T, ? extends U> pair)
    {
        map.put(pair.getKey(),pair.getValue());
    }
}
