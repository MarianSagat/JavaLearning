import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class DynamicReport implements IReport{
    List<TestResult<?>> testResultList;
    String header;
    String footer;
    Path logoPath;
    DynamicReport()
    {
        testResultList = new ArrayList<>();
    }
    @Override
    public List<TestResult<?>> getTestResults() {
        return testResultList;
    }

    @Override
    public String getHeader() {
        return header;
    }

    @Override
    public String getFooter() {
        return footer;
    }

    @Override
    public Path getLogoPath() {
        return logoPath;
    }

    @Override
    public void generatePDF()
    {

    }
}
