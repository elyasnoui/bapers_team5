package System;

import java.io.FileOutputStream;
import java.util.ArrayList;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.util.List;

public class Reportpdf {

    private static String FILE = "data/reports\\Reportpdf.pdf";
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);


    public static String getFILE() {
        return FILE;
    }



    public static List<String[]> getPerformanceData() {
        List<String[]> data = new ArrayList<String[]>();
        String[] tableTitleList = {"Name", "Task ID", "Department", "Date", "Start Time", "Time Taken"};
        data.add(tableTitleList);
        for (int i = 0; i < 10;) {
            List<String[]> dataLine = new ArrayList<String[]>();
            i++;
            String[] temp = new String[tableTitleList.length];
            for (int j = 0; j < tableTitleList.length; j++) {
                /*temp[j] =  (tableTitleList[j] + " " + i);*/


            }
            data.add(temp);
        }
        return data;
    }




    // iText allows to add metadata to the PDF which can be viewed in your Adobe
    // Reader
    // under File -> Properties
    public static void addMetaData(Document document) {

        document.addTitle("Report");
        document.addSubject("Printable Report");
        document.addKeywords("Report, PDF, iText");
        document.addAuthor("Team 5");
        document.addCreator("Team 5");
    }


    private static void createPerformanceTable(Section subCatPart)
            throws BadElementException {

        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100);
        List<String[]> dataset = getPerformanceData();
        for (String[] record : dataset) {
            for (String field : record) {
                table.addCell(field);
            }
        }

        subCatPart.add(table);
    }



    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    private Document createJobReportTemplate() {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("data/reports/jobreport.pdf"));
            document.open();
            addMetaData(document);
            //addTitlePage(document);
            return document;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Document createCustomerSalesReport() {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("data/reports/customersales.pdf"));
            document.open();
            addMetaData(document);
            //addTitlePage(document);
            return document;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void createSummaryReportTemplate(){
        try{
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("data/reports/summaryReport.pdf"));
            document.open();
            addMetaData(document);
            //addTitlePage(document);
            document.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private Document createPerformanceReportTemplate() {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("data/reports/performanceReport.pdf"));
            document.open();
            addMetaData(document);
            //addTitlePage(document);
            return document;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<String[]> insertPerformanceData(List<String[]> pd) {
        //Sort here


        //Before this
        List<String[]> data = new ArrayList<String[]>();
        String[] tableTitleList = {"Name", "Task ID", "Department", "Date", "Start Time", "Time Taken"};
        data.add(tableTitleList);
        for (int i = 0; i < pd.size(); i++) {
            List<String[]> dataLine = new ArrayList<String[]>();
            String[] temp = new String[tableTitleList.length];
            for (int j = 0; j < tableTitleList.length; j++) {
                /*temp[j] =  (tableTitleList[j] + " " + i);*/

                temp[j] = pd.get(i)[j];
            }
            data.add(temp);
        }
        return data;
    }

    private List<String[]> insertcustomerSalesData(List<String[]> jd) {
        //Sort here


        //Before this
        List<String[]> data = new ArrayList<String[]>();
        String[] tableTitleList = {"Company Name", "Customer Name", "Job ID", "Start Date", "End Date", "Status", "Amount", "Total" };
        data.add(tableTitleList);
        for (int i = 0; i < jd.size(); i++) {
            List<String[]> dataLine = new ArrayList<String[]>();
            String[] temp = new String[tableTitleList.length];
            for (int j = 0; j < tableTitleList.length; j++) {
                /*temp[j] =  (tableTitleList[j] + " " + i);*/

                temp[j] = jd.get(i)[j];
            }
            data.add(temp);
        }
        return data;
    }

    private List<String[]> insertjobReportData(List<String[]> jd) {
        //Sort here


        //Before this
        List<String[]> data = new ArrayList<String[]>();
        String[] tableTitleList = {"Company Name", "Customer Name", "Job ID", "Start Date", "End Date", "Status", "Amount", "Total" };
        data.add(tableTitleList);
        for (int i = 0; i < jd.size(); i++) {
            List<String[]> dataLine = new ArrayList<String[]>();
            String[] temp = new String[tableTitleList.length];
            for (int j = 0; j < tableTitleList.length; j++) {
                /*temp[j] =  (tableTitleList[j] + " " + i);*/

                temp[j] = jd.get(i)[j];
            }
            data.add(temp);
        }
        return data;
    }

    public void createPerformanceReport(List<String[]> performanceReportData) throws DocumentException {
        Document doc = createPerformanceReportTemplate();

        Paragraph anchor = new Paragraph("Individual Performance Report", catFont);
        //anchor.setName("Individual Performance Report");

        addEmptyLine(anchor,4);

        // Second parameter is the number of the chapter
        Chapter catPart = new Chapter(new Paragraph(anchor), 1);

        Paragraph subPara = new Paragraph("Subcategory 1", subFont);
        //Section subCatPart = catPart.addSection(subPara);
        //subCatPart.add(new Paragraph("Hello"));
        addEmptyLine(subPara, 5);

        // add performance table
        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100);
        List<String[]> dataset = insertPerformanceData(performanceReportData);
        for (String[] record : dataset) {
            for (String field : record) {
                table.addCell(field);
            }
        }

        // now add all this to the document
        doc.add(catPart);

        doc.add(table);

        //subCatPart.add(table);

        doc.close();
    }


    public void createCustomerSalesReport(List<String[]> customerSalesData) throws DocumentException {
        Document doc = createCustomerSalesReport();

        Paragraph anchor = new Paragraph("Customer Sales Report", catFont);
        //anchor.setName("Individual Performance Report");

        addEmptyLine(anchor,4);

        // Second parameter is the number of the chapter
        Chapter catPart = new Chapter(new Paragraph(anchor), 1);

        Paragraph subPara = new Paragraph("Subcategory 1", subFont);
        //Section subCatPart = catPart.addSection(subPara);
        //subCatPart.add(new Paragraph("Hello"));
        addEmptyLine(subPara, 5);

        // add performance table
        PdfPTable table = new PdfPTable(8);
        table.setWidthPercentage(100);
        List<String[]> dataset = insertcustomerSalesData(customerSalesData);
        for (String[] record : dataset) {
            for (String field : record) {
                table.addCell(field);
            }
        }

        // now add all this to the document
        doc.add(catPart);

        doc.add(table);

        //subCatPart.add(table);

        doc.close();
    }


    public void createJobReport(List<String[]> jobReportData) throws DocumentException {
        Document doc = createJobReportTemplate();

        Paragraph anchor = new Paragraph("Customer Job Report", catFont);
        //anchor.setName("Individual Performance Report");

        addEmptyLine(anchor,4);

        // Second parameter is the number of the chapter
        Chapter catPart = new Chapter(new Paragraph(anchor), 1);

        Paragraph subPara = new Paragraph("Subcategory 1", subFont);
        //Section subCatPart = catPart.addSection(subPara);
        //subCatPart.add(new Paragraph("Hello"));
        addEmptyLine(subPara, 5);

        // add performance table
        PdfPTable table = new PdfPTable(8);
        table.setWidthPercentage(100);
        List<String[]> dataset = insertcustomerSalesData(jobReportData);
        for (String[] record : dataset) {
            for (String field : record) {
                table.addCell(field);
            }
        }

        // now add all this to the document
        doc.add(catPart);

        doc.add(table);

        //subCatPart.add(table);

        doc.close();
    }
}



