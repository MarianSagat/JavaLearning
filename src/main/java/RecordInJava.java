import java.util.UUID;

/** an analog of a C++ struct **/
public record RecordInJava(
        String suiteName,
        String testName,
        UUID id
){}
