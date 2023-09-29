package JavaReflectionAPI;

import org.testng.annotations.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.BitSet;

public class ClassInstanceTest
{
    @Test
    public void test()
    {
        String obj = "string object";
        System.out.println(obj.getClass());
        //Class<String> stringClass = obj.getClass(); compile error
        Class<? extends String> stringClass = obj.getClass();
        System.out.println(stringClass);
        Class<? extends Object> stringClass_2 = obj.getClass();
        System.out.println(stringClass_2);
        Class<String> classOfString = String.class;
        String className = "java.lang.String";
        try
        {
            Class<?> stringClass_3 = Class.forName(className);
            System.out.println(stringClass_3);
        }
        catch (ClassNotFoundException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void test_2()
    {
        Class<?> classs = "hello".getClass();
        System.out.println(classs);
        Class<?> superClass = classs.getClass();
        System.out.println(superClass);
        Class<?>[] interfaces = classs.getInterfaces();
        /**
         * it needs to be wraped to string methods otherwise you will get nonsense string :D
         */
        System.out.println(Arrays.toString(interfaces));
    }

    @Test
    public void testFields()
    {
        class Person
        {
            private int age;
            private String name;
        }

        Class<?> classs = Person.class;
        System.out.println(classs);
        Field[] fields = classs.getFields();
        System.out.println(Arrays.toString(fields));
        Field[] allFields = classs.getDeclaredFields();
        System.out.println(Arrays.toString(allFields));
    }

    @Test
    public void testMethods()
    {
        class Person
        {
            String name;

            public void setName(String name)
            {
                this.name = name;
            }
        }

        Person person = new Person();
        System.out.println(Arrays.toString(person.getClass().getMethods()));
        System.out.println(Arrays.toString(person.getClass().getDeclaredMethods()));
    }

    @Test
    public void modifiersTest()
    {
        class Person
        {
           String name;
           String nameNotPublic;
           public String namePublic;

            public void setName(String name)
            {
                this.name = name;
            }
        }

        try
        {
            Field field_2 = Person.class.getDeclaredField("nameNotPublic");
            System.out.println(field_2.getModifiers() & 1);
            System.out.println(Modifier.isPublic(field_2.getModifiers()));

            Field field_3 = Person.class.getDeclaredField("namePublic");
            System.out.println(field_3.getModifiers() & 1);
            System.out.println(Modifier.isPublic(field_3.getModifiers()));



            Field field = Person.class.getField("name"); //it has to be public -> NoSuchFieldException
            System.out.println(field.getModifiers() & 1);
        }
        catch (NoSuchFieldException e)
        {
            throw new RuntimeException(e);
        }
    }
}
