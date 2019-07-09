/*
    This file is part of the iText (R) project.
    Copyright (c) 1998-2019 iText Group NV
    Authors: iText Software.

    For more information, please contact iText Software at this address:
    sales@itextpdf.com
 */

package com.itextpdf.samples.typography.arabic;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.licensekey.LicenseKey;
import com.itextpdf.test.annotations.type.SampleTest;
import org.junit.experimental.categories.Category;

import java.io.File;
import java.io.IOException;

@Category(SampleTest.class)
public class ArabicWordSpacing {

    public static final String DEST = "./target/samples/typography/ArabicWordSpacing.pdf";
    public static final String FONTS_FOLDER = "./src/main/java/com/itextpdf/samples/typography/arabic/resources/";

    public static void main(String[] args) throws Exception {

        // Load the license file to use typography features
        LicenseKey.loadLicenseFile(System.getenv("ITEXT7_LICENSEKEY") + "/itextkey-typography.xml");

        File file = new File(DEST);
        file.getParentFile().mkdirs();

        new ArabicWordSpacing().createPDF(DEST);
    }

    public void createPDF(String dest) throws IOException {

        // Create a pdf document along with a Document (default root layout element) instance
        PdfDocument pdfDocument = new PdfDocument(new PdfWriter(dest));
        Document document = new Document(pdfDocument);

        //  في القيام بنشاط
        String text = "\u0641\u064A\u0020\u0627\u0644\u0642\u064A\u0627\u0645\u0020\u0628\u0646\u0634\u0627\u0637";

        PdfFont font = PdfFontFactory.createFont(FONTS_FOLDER + "NotoNaskhArabic-Regular.ttf",
                PdfEncodings.IDENTITY_H);

        // Overwrite some default document font-related properties. From now on they will be used for all the elements
        // added to the document unless they are overwritten inside these elements
        document
                .setFont(font)
                .setFontSize(10);

        // Create paragraph, add string to it with the default word spacing and add all to the document
        document.add(createParagraph(text));

        // Add text with a word spacing of 10 points
        document.add(createParagraph(text).setWordSpacing(10));

        // Set word spacing on the document. It will be applied to the next paragraph
        document.setWordSpacing(20);
        document.add(createParagraph(text));

        document.close();
    }

    // This method creates a paragraph with right text alignment
    private static Paragraph createParagraph(String text) {
        Paragraph paragraph = new Paragraph(text);

        //In Arabic text goes from right to left, that's one we need to overwrite the default iText's alignment
        paragraph.setTextAlignment(TextAlignment.RIGHT);
        return paragraph;
    }
}
