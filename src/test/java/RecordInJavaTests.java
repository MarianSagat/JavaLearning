import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.UUID;

public class RecordInJavaTests
{
    @Test
    public void simpleTest(){
        final UUID id = UUID.randomUUID();
        RecordInJava record = new RecordInJava("RecordsInJava","simpleTest", id);
        RecordInJava expectedRecord = new RecordInJava("RecordsInJava","simpleTest",id);
        Assert.assertEquals(record.testName(),expectedRecord.testName());
        Assert.assertEquals(record.id(),expectedRecord.id());
        Assert.assertEquals(record.suiteName(),expectedRecord.suiteName());
    }
}
