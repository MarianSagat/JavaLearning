import java.nio.file.Path;
import java.util.List;

public interface IReport {

    List<TestResult<?>> getTestResults();
    String getHeader();
    String getFooter();
    Path getLogoPath();

    void generatePDF();
}
