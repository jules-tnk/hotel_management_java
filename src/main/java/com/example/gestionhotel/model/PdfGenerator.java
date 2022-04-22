package com.example.gestionhotel.model;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.FileNotFoundException;

public class PdfGenerator {
    public static void main(String args[]) {
        createPdf();
    }

    public static void createPdf(){
        // Creating a PdfWriter
        String dest = "doc.pdf";
        PdfWriter writer = null;
        try {
            writer = new PdfWriter(dest);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Creating a PdfDocument
        PdfDocument pdfDoc = new PdfDocument(writer);

        // Adding a new page
        pdfDoc.addNewPage();
        pdfDoc.addNewPage();
        pdfDoc.addNewPage();

        // Creating a Document
        Document document = new Document(pdfDoc);

        // Closing the document
        document.close();
        System.out.println("PDF Created");
    }

    public static void editPdf(){
        // Creating a PdfWriter
        String dest = "./../doc.pdf";
        PdfWriter writer = null;
        try {
            writer = new PdfWriter(dest);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Creating a PdfDocument
        PdfDocument pdf = new PdfDocument(writer);

        // Creating a Document
        Document document = new Document(pdf);
        String para1 = "Tutorials Point originated from the idea that there exists";

        String para2 = "The journey commenced with a single tutorial on HTML in 2006 ";

        // Creating Paragraphs
        Paragraph paragraph1 = new Paragraph(para1);
        Paragraph paragraph2 = new Paragraph(para2);

        // Adding paragraphs to document
        document.add(paragraph1);
        document.add(paragraph2);

        // Closing the document
        document.close();
        System.out.println("Paragraph added");
    }
}
