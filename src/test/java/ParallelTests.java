import junit.framework.Assert;
import org.junit.jupiter.api.Test;

public class ParallelTests
{
    @Test
    public void test()
    {
        Thread sleepingBeautyInterrupted = new Thread(
                () -> {
                    try
                    {
                        Thread.sleep(4000);
                    }
                    catch(Exception exception)
                    {
                        System.out.println("error ");
                        Thread.currentThread().interrupt();
                        //EACH FUNCTION AS SLEEP/WAIT ... WILL THROW INTERUPTED EXCEPTION
                        //NO CUSTOM SHUTDOWN - INTERRUPT IS SETTING ONLY SOME FLAG,
                        //WHICH IS NOT CAUSING INTERRUPTION OF THREAD AT ALL
                       // throw new NoSuchMethodError();

                    }

                    while(true)
                    {
                        /**
                         * This will be executed, because  Thread.currentThread().interrupt();
                         * only change status of thread, it will not shout down it
                         */
                        System.out.println("Beauty is not sleeping anymore.");
                    }
                }
        );

        sleepingBeautyInterrupted.start();

        int count = 0;
        while(true)
        {
            System.out.println("Main thread is still running");
            count++;
            if(count == 2)
            {
                sleepingBeautyInterrupted.interrupt();
            }
        }
    }
}