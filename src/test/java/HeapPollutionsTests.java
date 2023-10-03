import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class HeapPollutionsTests
{
    @Test
    public void heatPollution()
    {
        //LIST RAW TYPE
        List dogs = new ArrayList<Dog>();
        dogs.add(new Dog());
        dogs.add(new Dog());

        List<Cat> cats = dogs; //ALLOWED OMG! :D
        Cat cat = cats.get(0);//JAVA.LANG.CLASSCASTEXCEPTION !!!

        List<Dog> dogs1 = new ArrayList<>();
        dogs1.add(new Dog());

        List<? extends Animal> animals = dogs1;
        //it is trying to convert one concrete but unknown type to List<Dog>, but iside animals can be different types!!!
        List<Dog> dogsAgain = (List<Dog>) animals; //USES UNCHECKED OR UNSAFE OPERATIONS.

    }
    interface Animal
    {

    }

    class Dog implements Animal
    {

        @Override
        public String toString()
        {
            return "haf";
        }
    }

    class Cat implements Animal
    {
        @Override
        public String toString()
        {
            return "mnau";
        }
    }

}
