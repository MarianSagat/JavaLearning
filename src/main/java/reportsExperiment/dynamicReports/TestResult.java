import java.nio.file.Path;

public record TestResult<T>(
        String testName,
        String description,

        Path imageResult,
        T expectedValue,
        T value,
        boolean result) {}
