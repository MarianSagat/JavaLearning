import org.testng.annotations.Test;

public class ThrowableTests {

    @Test
    public void testThrowable() {

        try {
            throw new Error("Test error");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        //Error
        catch (Error error)
        //catch(Throwable error)
        {
            //TcLog
            error.printStackTrace();
            throw error;
        }
    }
}
