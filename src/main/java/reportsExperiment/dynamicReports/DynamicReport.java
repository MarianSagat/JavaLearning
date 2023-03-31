import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.awt.Color;
import java.math.BigDecimal;

import net.sf.dynamicreports.report.builder.chart.Bar3DChartBuilder;
import net.sf.dynamicreports.report.builder.column.PercentageColumnBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.datatype.BigDecimalType;
import net.sf.dynamicreports.report.builder.group.ColumnGroupBuilder;
import net.sf.dynamicreports.report.builder.style.ConditionalStyleBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.constant.VerticalTextAlignment;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;

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

    //https://github.com/blazart/dynamic-reports/blob/master/dynamicreports-examples/src/main/java/net/sf/dynamicreports/examples/gettingstarted/SimpleReport_Step12.java
    @Override
    public void generatePDF_SimpleReport_Step12()
    {
        CurrencyType currencyType = new CurrencyType();

        StyleBuilder boldStyle         = stl.style().bold();
        StyleBuilder boldCenteredStyle = stl.style(boldStyle).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER);
        StyleBuilder columnTitleStyle  = stl.style(boldCenteredStyle)
                .setBorder(stl.pen1Point())
                .setBackgroundColor(Color.LIGHT_GRAY);
        StyleBuilder titleStyle        = stl.style(boldCenteredStyle)
                .setVerticalTextAlignment(VerticalTextAlignment.MIDDLE)
                .setFontSize(15);

        //                                                           title,     field name     data type
        TextColumnBuilder<String>     itemColumn      = col.column("Item",       "item",      type.stringType()).setStyle(boldStyle);
        TextColumnBuilder<Integer>    quantityColumn  = col.column("Quantity",   "quantity",  type.integerType());
        TextColumnBuilder<BigDecimal> unitPriceColumn = col.column("Unit price", "unitprice", currencyType);
        //price = unitPrice * quantity
        TextColumnBuilder<BigDecimal> priceColumn     = unitPriceColumn.multiply(quantityColumn).setTitle("Price")
                .setDataType(currencyType);
        PercentageColumnBuilder       pricePercColumn = col.percentageColumn("Price %", priceColumn);
        TextColumnBuilder<Integer>    rowNumberColumn = col.reportRowNumberColumn("No.")
                //sets the fixed width of a column, width = 2 * character width
                .setFixedColumns(2)
                .setHorizontalTextAlignment(HorizontalTextAlignment.CENTER);
        Bar3DChartBuilder itemChart = cht.bar3DChart()
                .setTitle("Sales by item")
                .setCategory(itemColumn)
                .addSerie(
                        cht.serie(unitPriceColumn), cht.serie(priceColumn));
        Bar3DChartBuilder itemChart2 = cht.bar3DChart()
                .setTitle("Sales by item")
                .setCategory(itemColumn)
                .setUseSeriesAsCategory(true)
                .addSerie(
                        cht.serie(unitPriceColumn), cht.serie(priceColumn));
        ColumnGroupBuilder itemGroup = grp.group(itemColumn);
        itemGroup.setPrintSubtotalsWhenExpression(exp.printWhenGroupHasMoreThanOneRow(itemGroup));

        ConditionalStyleBuilder condition1 = stl.conditionalStyle(cnd.greater(priceColumn, 150))
                .setBackgroundColor(new Color(210, 255, 210));
        ConditionalStyleBuilder condition2 = stl.conditionalStyle(cnd.smaller(priceColumn, 30))
                .setBackgroundColor(new Color(255, 210, 210));
        ConditionalStyleBuilder condition3 = stl.conditionalStyle(cnd.greater(priceColumn, 200))
                .setBackgroundColor(new Color(0, 190, 0))
                .bold();
        ConditionalStyleBuilder condition4 = stl.conditionalStyle(cnd.smaller(priceColumn, 20))
                .setBackgroundColor(new Color(190, 0, 0))
                .bold();
        StyleBuilder priceStyle = stl.style()
                .conditionalStyles(
                        condition3, condition4);
        priceColumn.setStyle(priceStyle);
        try {
            report()//create new report design
                    .setColumnTitleStyle(columnTitleStyle)
                    .setSubtotalStyle(boldStyle)
                    .highlightDetailEvenRows()
                    .columns(//add columns
                            rowNumberColumn, itemColumn, quantityColumn, unitPriceColumn, priceColumn, pricePercColumn)
                    .columnGrid(
                            rowNumberColumn, quantityColumn, unitPriceColumn, grid.verticalColumnGridList(priceColumn, pricePercColumn))
                    .groupBy(itemGroup)
                    .subtotalsAtSummary(
                            sbt.sum(unitPriceColumn), sbt.sum(priceColumn))
                    .subtotalsAtFirstGroupFooter(
                            sbt.sum(unitPriceColumn), sbt.sum(priceColumn))
                    .detailRowHighlighters(
                            condition1, condition2)
                    .title(//shows report title
                            cmp.horizontalList()
                                    .add(
                                            cmp.image(Templates.class.getResource("images/dynamicreports.png")).setFixedDimension(80, 80),
                                            cmp.text("DynamicReports").setStyle(titleStyle).setHorizontalTextAlignment(HorizontalTextAlignment.LEFT),
                                            cmp.text("Getting started").setStyle(titleStyle).setHorizontalTextAlignment(HorizontalTextAlignment.RIGHT))
                                    .newRow()
                                    .add(cmp.filler().setStyle(stl.style().setTopBorder(stl.pen2Point())).setFixedHeight(10)))
                    .pageFooter(cmp.pageXofY().setStyle(boldCenteredStyle))//shows number of page at page footer
                    .summary(
                            cmp.horizontalList(itemChart, itemChart2))
                    .setDataSource(createDataSource())//set datasource
                    .toPdf(new FileOutputStream("output.pdf"));//create and show report
        } catch (DRException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private class CurrencyType extends BigDecimalType {
        private static final long serialVersionUID = 1L;

        @Override
        public String getPattern() {
            return "$ #,###.00";
        }
    }

    private JRDataSource createDataSource() {
        DRDataSource dataSource = new DRDataSource("item", "quantity", "unitprice");
        dataSource.add("Notebook", 1, new BigDecimal(500));
        dataSource.add("DVD", 5, new BigDecimal(30));
        dataSource.add("DVD", 1, new BigDecimal(28));
        dataSource.add("DVD", 5, new BigDecimal(32));
        dataSource.add("Book", 3, new BigDecimal(11));
        dataSource.add("Book", 1, new BigDecimal(15));
        dataSource.add("Book", 5, new BigDecimal(10));
        dataSource.add("Book", 8, new BigDecimal(9));
        return dataSource;
    }
}
