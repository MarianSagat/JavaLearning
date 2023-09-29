import org.testng.annotations.Test;

import java.lang.annotation.*;


public class AnotationTests
{

    @Retention(RetentionPolicy.RUNTIME) // This annotation should be retained at runtime
    @Target(ElementType.METHOD) // This annotation can only be applied to methods
    public @interface MyCustomAnnotation
    {
        String value() default ""; // Define an attribute for the annotation
    }

    @MyCustomAnnotation(value = "This is a custom annotation")
    public void myMethod()
    {
        // Your method logic here
    }

    public void anotherMethod()
    {
        // Your method logic here
    }

    @Test
    public  void customAnotationTest()
    {
        AnotationTests obj = new AnotationTests();
        obj.myMethod();

        // Check if the custom annotation is present and access its values
        try
        {
            // Get the class's method by name
            java.lang.reflect.Method method = obj.getClass().getMethod("myMethod");

            // Check if the custom annotation is present
            if (method.isAnnotationPresent(MyCustomAnnotation.class))
            {
                // Get the annotation instance
                MyCustomAnnotation annotation = method.getAnnotation(MyCustomAnnotation.class);

                // Access the annotation's value
                String annotationValue = annotation.value();

                // Print the annotation value
                System.out.println("Custom Annotation Value: " + annotationValue);
            }
        }
        catch (NoSuchMethodException e)
        {
            e.printStackTrace();
        }
    }
}
