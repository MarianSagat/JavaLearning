import java.nio.file.Path;
import java.util.List;

public interface IReport {

    List<TestResult<?>> getTestResults();
    String getHeader();
    String getFooter();
    Path getLogoPath();

    void generatePDF();
    void generatePDF_SimpleReport_Step12();

    // TODO: 01/04/2023 somehow change gradle settings in order to do not change my source folders to only one folder!
}
