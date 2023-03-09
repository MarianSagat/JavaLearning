import org.testng.Assert;
import org.testng.annotations.Test;

public class AdditionForAllanTests
{
    public Double doCalculation(String n1, String n2)
    {
        try
        {
            return Double.parseDouble(n1) + Double.parseDouble(n2);
        }
        catch(NumberFormatException e)
        {
            //e.printStackTrace();
            System.out.println("Invalid imputs");
        }
        catch(NullPointerException e)
        {
            //e.printStackTrace();
            System.out.println("One or both are null.");
        }
        return null;
    }

    @Test
    public void testAddition()
    {

        Assert.assertEquals(doCalculation("1.014", "5.2").compareTo(Double.valueOf(1.014d + 5.2d)), 0);
        System.out.println(doCalculation("1.014", "5.2"));
        System.out.println(Double.valueOf(1.014d + 5.2d));
    }

    @Test
    public void testAddition2()
    {
        var first = doCalculation("1.000014", "5.2");
        var second = Double.valueOf(1.000014d + 5.2d);
        Assert.assertEquals(first.compareTo(second), 0);
        System.out.println(first);
        System.out.println(second);
    }

    @Test
    public void testAddition3()
    {

        var first = doCalculation("1.000014", "5.00002");
        var second = Double.valueOf(1.000014d + 5.00002d);
        Assert.assertTrue(first.compareTo(second) == 0);
        System.out.println(first);
        System.out.println(second);
    }

    @Test
    public void testAddition4()
    {

        var first = doCalculation("1.000014", "5.00009");
        var second = Double.valueOf(1.000014d + 5.00009d);
        Assert.assertTrue(first.compareTo(second) == 0);
        System.out.println(first);
        System.out.println(second);
    }

    @Test
    public void testFailingAddition()
    {
        Assert.assertNull(doCalculation("str", "5.00009"));
        Assert.assertNull(doCalculation(null, "5.00009"));
    }
}
