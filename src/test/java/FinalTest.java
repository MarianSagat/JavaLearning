import org.testng.Assert;
import org.testng.annotations.Test;

public class FinalTest
{
    class Foo
    {
        private String field;

        Foo(String field){this.field = field;}

        public void setField(String field)
        {
            this.field = field;
        }

        public String getField()
        {
            return field;
        }
    }

    static void updateMehod(final Foo foo)
    {
        foo.setField("bla bla");
    }

    @Test
    public void testFinal()
    {
        Foo foo = new Foo("this");
        updateMehod(foo);
        Assert.assertEquals(foo.getField(),"bla bla");
    }
}
