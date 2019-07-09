/*
    This file is part of the iText (R) project.
    Copyright (c) 1998-2019 iText Group NV
    Authors: iText Software.

    For more information, please contact iText Software at this address:
    sales@itextpdf.com
 */

package com.itextpdf.samples.typography.bengali;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.annot.PdfAnnotation;
import com.itextpdf.kernel.pdf.annot.PdfStampAnnotation;
import com.itextpdf.licensekey.LicenseKey;
import com.itextpdf.test.annotations.type.SampleTest;
import org.junit.experimental.categories.Category;

import java.io.File;
import java.io.IOException;

@Category(SampleTest.class)
public class BengaliStampAnnotation {

    public static final String DEST = "./target/samples/typography/BengaliStampAnnotation.pdf";

    public static void main(String[] args) throws Exception {

        // Load the license file to use typography features
        LicenseKey.loadLicenseFile(System.getenv("ITEXT7_LICENSEKEY") + "/itextkey-typography.xml");

        File file = new File(DEST);
        file.getParentFile().mkdirs();

        new BengaliStampAnnotation().createPDF(DEST);
    }

    public void createPDF(String dest) throws IOException {
        PdfDocument pdfDocument = new PdfDocument(new PdfWriter(dest));

        // সুরে মিললে সুর হবে
        String line1 = "\u09B8\u09C1\u09B0\u09C7\u0020\u09AE\u09BF\u09B2\u09B2\u09C7\u0020\u09B8\u09C1\u09B0\u0020\u09B9" +
                "\u09AC\u09C7";

        Rectangle rectangleAnnot = new Rectangle(55, 650, 35, 35);

        // Create a stamp annotation and set some properties
        PdfAnnotation annotation = new PdfStampAnnotation(rectangleAnnot);
        annotation
                .setContents(line1)
                .setColor(ColorConstants.CYAN);

        // Add an empty page to the document, then add the annotation to the page
        PdfPage page = pdfDocument.addNewPage();
        page.addAnnotation(annotation);

        pdfDocument.close();
    }
}

