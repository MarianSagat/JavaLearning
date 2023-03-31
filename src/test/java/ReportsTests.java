import org.testng.annotations.Test;

import java.nio.file.Path;
import java.time.LocalDate;


public class ReportsTests {

    @Test
    public void testReport() {
        ReportBuilder builder = new ReportBuilder();
        IReport report = builder
                .withHeader("Project JavaLearning")
                .withLogo(Path.of("src/test/testResources/Basic-Web/img/logo.png"))
                .withFooter("Test results for suite with %s tests. Date:%s "
                        .formatted("4")
                        .formatted(LocalDate.now()))
                .with(new TestResult("testBoolean",
                        "Simple test only for visulisation",
                        Path.of("src/test/testResources/Basic-Web/img/team1.png"),
                        true,
                        true,
                        true))
                .with(new TestResult("testString",
                        "Simple string test only for visulisation, now I am trying to make long line" +
                                "I want to see how it handle this long description",
                        Path.of("src/test/testResources/Basic-Web/img/team2.png"),
                        "nice short string",
                        "nice longer string",
                        false))
                .with(new TestResult("testString",
                        "Simple string test only for visulisation, now I am trying to make long line" +
                                "I want to see how it handle this long description. Now i will try it make even longer" +
                                "I hope I will be successfull trial.",
                        Path.of("src/test/testResources/Basic-Web/img/team3.png"),
                        "next time try instead of this string some record",
                        "next time try instead of this string some record",
                        true))
                .build();

        report.generatePDF();
    }
}
