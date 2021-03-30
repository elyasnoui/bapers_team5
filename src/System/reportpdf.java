package System;

import java.io.FileOutputStream;
import java.util.Date;

import GUI.Report;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


public class reportpdf {

    private static String FILE = "data/reports\\reportpdf.pdf";
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


    public static void jobReport(){
        try{
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(FILE));
            document.open();
            addMetaData(document);
            //addTitlePage(document);
            addjobContent(document);
            document.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void summaryReport(){
        try{
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(FILE));
            document.open();
            addMetaData(document);
            //addTitlePage(document);
            addsummaryContent(document);
            document.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void performanceReport() {
        System.out.println("here");
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(FILE));
            document.open();
            addMetaData(document);
            //addTitlePage(document);
            addperformanceContent(document);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
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



    public static void addTitlePage(Document document)
            throws DocumentException {
        Paragraph preface = new Paragraph();
        // We add one empty line
        addEmptyLine(preface, 1);
        // Lets write a big header
        preface.add(new Paragraph("Title of the document", catFont));

        addEmptyLine(preface, 1);
        // Will create: Report generated by: _name, _date
        preface.add(new Paragraph(
                "Report generated by: " + System.getProperty("user.name") + ", " + new Date(), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                smallBold));
        addEmptyLine(preface, 3);
        preface.add(new Paragraph(
                "For report printing soon",
                smallBold));

        addEmptyLine(preface, 3);

        preface.add(new Paragraph(
                "Need to now add report to this",
                redFont));

        document.add(preface);
        // Start a new page
        //document.newPage();
    }

    public static void addperformanceContent(Document document) throws DocumentException {
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
        createPerformanceTable(catPart);

        // now add all this to the document
        document.add(catPart);

    }

    public static void addsummaryContent(Document document) throws DocumentException {
        Paragraph anchor = new Paragraph("Summary Performance Report", catFont);
        //anchor.setName("Summary Performance Report");

        addEmptyLine(anchor,4);
        // Second parameter is the number of the chapter
        Chapter catPart = new Chapter(new Paragraph(anchor), 1);

        Paragraph subPara = new Paragraph("Summary Performance Report from:", subFont);
        //Section subCatPart = catPart.addSection(subPara);
        //subCatPart.add(new Paragraph("Hello"));
        addEmptyLine(subPara,5);


        // add a table
        createSummaryTable(catPart);


        document.add(catPart);

    }

    public static void addjobContent(Document document) throws DocumentException {
        Paragraph anchor = new Paragraph("Customer Job Report", catFont);
       // anchor.setName("Customer Job Report");

        addEmptyLine(anchor,4);

        // Second parameter is the number of the chapter
        Chapter catPart = new Chapter(new Paragraph(anchor), 1);

        // add a table
        createJobTable(catPart);

        // now add all this to the document
        document.add(catPart);

    }
    private static void createSummaryTable(Section subCatPart)
            throws BadElementException {

        //dayshift 1

        PdfPTable table = new PdfPTable(5);

        // t.setBorderColor(BaseColor.GRAY);
        // t.setPadding(4);
        // t.setSpacing(4);
        // t.setBorderWidth(1);
        // table.isLockedWidth();

        Paragraph dayShift1 = new Paragraph("Day Shift 1: (5am-2:30pm)",subFont);

        subCatPart.add(dayShift1);

        addEmptyLine(dayShift1,1);

        PdfPCell c1 = new PdfPCell(new Phrase("Date"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Copy Room"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Development"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Finishing"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Packing"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);



        table.setHeaderRows(1);

        table.addCell("1.0");
        table.addCell("1.1");
        table.addCell("1.2");
        table.addCell("1.3");
        table.addCell("1.4");
        table.addCell("1.5");
        table.addCell("2.0");
        table.addCell("2.1");
        table.addCell("2.2");
        table.addCell("2.3");

        subCatPart.add(table);

        //day shift 2

        Paragraph dayShift2 = new Paragraph("Day Shift 2: (2:30pm-10pm)",subFont);

        subCatPart.add(dayShift2);

        addEmptyLine(dayShift2,1);

        PdfPTable table2 = new PdfPTable(5);

        PdfPCell c2 = new PdfPCell(new Phrase("Date"));
        c2.setHorizontalAlignment(Element.ALIGN_CENTER);
        table2.addCell(c2);

        c2 = new PdfPCell(new Phrase("Copy Room"));
        c2.setHorizontalAlignment(Element.ALIGN_CENTER);
        table2.addCell(c2);

        c2 = new PdfPCell(new Phrase("Development"));
        c2.setHorizontalAlignment(Element.ALIGN_CENTER);
        table2.addCell(c2);

        c2 = new PdfPCell(new Phrase("Finishing"));
        c2.setHorizontalAlignment(Element.ALIGN_CENTER);
        table2.addCell(c2);

        c2 = new PdfPCell(new Phrase("Packing"));
        c2.setHorizontalAlignment(Element.ALIGN_CENTER);
        table2.addCell(c2);

        table2.setHeaderRows(1);

        table2.addCell("1.0");
        table2.addCell("1.1");
        table2.addCell("1.2");
        table2.addCell("1.3");
        table2.addCell("1.4");
        table2.addCell("1.5");
        table2.addCell("2.0");
        table2.addCell("2.1");
        table2.addCell("2.2");
        table2.addCell("2.3");
        table2.addCell("2.4");
        table2.addCell("2.5");

        subCatPart.add(table2);

        //night shift

        PdfPTable table3 = new PdfPTable(5);

        // t.setBorderColor(BaseColor.GRAY);
        // t.setPadding(4);
        // t.setSpacing(4);
        // t.setBorderWidth(1);
        // table.isLockedWidth();

        Paragraph nightShift = new Paragraph("Night Shift: (10pm-5am)",subFont);

        subCatPart.add(nightShift);

        addEmptyLine(nightShift,1);

        PdfPCell c3 = new PdfPCell(new Phrase("Date"));
        c3.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c3 = new PdfPCell(new Phrase("Copy Room"));
        c3.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c3);

        c3 = new PdfPCell(new Phrase("Development"));
        c3.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c3);

        c3 = new PdfPCell(new Phrase("Finishing"));
        c3.setHorizontalAlignment(Element.ALIGN_CENTER);
        table3.addCell(c3);

        c3 = new PdfPCell(new Phrase("Packing"));
        c3.setHorizontalAlignment(Element.ALIGN_CENTER);
        table3.addCell(c3);



        table3.setHeaderRows(1);

        table3.addCell("1.0");
        table3.addCell("1.1");
        table3.addCell("1.2");
        table3.addCell("1.3");
        table3.addCell("1.4");
        table3.addCell("1.5");
        table3.addCell("2.0");
        table3.addCell("2.1");
        table3.addCell("2.2");
        table3.addCell("2.3");

        subCatPart.add(table3);


        //For period/total

        PdfPTable table4 = new PdfPTable(5);

        // t.setBorderColor(BaseColor.GRAY);
        // t.setPadding(4);
        // t.setSpacing(4);
        // t.setBorderWidth(1);
        // table.isLockedWidth();

        Paragraph total = new Paragraph("For Period: (13/01/2020-20/01/2020)",subFont);

        subCatPart.add(total);

        addEmptyLine(total,1);

        PdfPCell c4 = new PdfPCell(new Phrase("Date"));
        c4.setHorizontalAlignment(Element.ALIGN_CENTER);
        table4.addCell(c4);

        c4 = new PdfPCell(new Phrase("Copy Room"));
        c4.setHorizontalAlignment(Element.ALIGN_CENTER);
        table4.addCell(c4);

        c4 = new PdfPCell(new Phrase("Development"));
        c4.setHorizontalAlignment(Element.ALIGN_CENTER);
        table4.addCell(c4);

        c4 = new PdfPCell(new Phrase("Finishing"));
        c4.setHorizontalAlignment(Element.ALIGN_CENTER);
        table4.addCell(c4);

        c4 = new PdfPCell(new Phrase("Packing"));
        c4.setHorizontalAlignment(Element.ALIGN_CENTER);
        table4.addCell(c4);



        table4.setHeaderRows(1);

        table4.addCell("1.0");
        table4.addCell("1.1");
        table4.addCell("1.2");
        table4.addCell("1.3");
        table4.addCell("1.4");
        table4.addCell("1.5");
        table4.addCell("2.0");
        table4.addCell("2.1");
        table4.addCell("2.2");
        table4.addCell("2.3");

        subCatPart.add(table4);



    }

    private static void createJobTable(Section subCatPart)
            throws BadElementException {

        PdfPTable table = new PdfPTable(6);

        // t.setBorderColor(BaseColor.GRAY);
        // t.setPadding(4);
        // t.setSpacing(4);
        // t.setBorderWidth(1);
        // table.isLockedWidth();


        PdfPCell c1 = new PdfPCell(new Phrase("Job ID"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Tasks"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Job Price"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("isPaid"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Start Date"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("End Date"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);



        table.setHeaderRows(1);

        table.addCell("1.0");
        table.addCell("1.1");
        table.addCell("1.2");
        table.addCell("1.3");
        table.addCell("1.4");
        table.addCell("1.5");
        table.addCell("1.6");
        table.addCell("2.0");
        table.addCell("2.1");
        table.addCell("2.2");



        subCatPart.add(table);

    }

    private static void createPerformanceTable(Section subCatPart)
            throws BadElementException {

        PdfPTable table = new PdfPTable(7);

        // t.setBorderColor(BaseColor.GRAY);
        // t.setPadding(4);
        // t.setSpacing(4);
        // t.setBorderWidth(1);
        // table.isLockedWidth();


        PdfPCell c1 = new PdfPCell(new Phrase("Name"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("ID Task"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Department"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Date"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Start Time"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Time Taken"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Total"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);



        table.setHeaderRows(1);

        table.addCell("1.0");
        table.addCell("1.1");
        table.addCell("1.2");
        table.addCell("1.3");
        table.addCell("1.4");
        table.addCell("1.5");
        table.addCell("1.6");
        table.addCell("2.0");
        table.addCell("2.1");
        table.addCell("2.2");
        table.addCell("2.3");
        table.addCell("2.4");
        table.addCell("2.5");
        table.addCell("2.6");


        subCatPart.add(table);

    }

    private static void createList(Section subCatPart) {
        List list = new List(true, false, 10);
        list.add(new ListItem("First point"));
        list.add(new ListItem("Second point"));
        list.add(new ListItem("Third point"));
        subCatPart.add(list);
    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}

