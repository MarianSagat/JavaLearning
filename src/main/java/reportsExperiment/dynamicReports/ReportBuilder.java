package reportsExperiment.dynamicReports;

import java.nio.file.Path;

public class ReportBuilder {

    private DynamicReport dynamicReport;

    public ReportBuilder()
    {
        dynamicReport = new DynamicReport();
    }



    public ReportBuilder withHeader(String header) {
        dynamicReport.header = header;
        return this;
    }

    public ReportBuilder withFooter(String footer) {
        dynamicReport.footer = footer;
        return this;
    }

    public ReportBuilder withLogo(Path logo)
    {
        dynamicReport.logoPath = logo;
        return this;
    }

    public ReportBuilder with(TestResult<?> testResult)
    {
        dynamicReport.testResultList.add(testResult);
        return this;
    }

    public IReport build()
    {
        return dynamicReport;
    }
}
