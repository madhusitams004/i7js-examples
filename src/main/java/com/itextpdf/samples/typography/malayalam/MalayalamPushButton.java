/*
    This file is part of the iText (R) project.
    Copyright (c) 1998-2019 iText Group NV
    Authors: iText Software.

    For more information, please contact iText Software at this address:
    sales@itextpdf.com
 */

package com.itextpdf.samples.typography.malayalam;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfButtonFormField;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.licensekey.LicenseKey;
import com.itextpdf.test.annotations.type.SampleTest;
import org.junit.experimental.categories.Category;

import java.io.File;
import java.io.IOException;

@Category(SampleTest.class)
public class MalayalamPushButton {

    public static final String DEST = "./target/samples/typography/MalayalamPushButton.pdf";
    public static final String FONTS_FOLDER = "./src/main/java/com/itextpdf/samples/typography/malayalam/resources/";

    public static void main(String[] args) throws Exception {

        // Load the license file to use typography features
        LicenseKey.loadLicenseFile(System.getenv("ITEXT7_LICENSEKEY") + "/itextkey-typography.xml");

        File file = new File(DEST);
        file.getParentFile().mkdirs();

        new MalayalamPushButton().createPDF(DEST);
    }

    public void createPDF(String dest) throws IOException {
        PdfDocument pdfDocument = new PdfDocument(new PdfWriter(dest));

        PdfAcroForm form = PdfAcroForm.getAcroForm(pdfDocument, true);

        // Embedded parameter indicates whether the font is to be embedded into the target document.
        // We set it to make sure that the resultant document looks the same within different environments.
        PdfFont font = PdfFontFactory.createFont(FONTS_FOLDER + "NotoSansMalayalam-Regular.ttf",
                PdfEncodings.IDENTITY_H, true);

        // എന്നെ തള്ളൂ
        String text = "\u0D0E\u0D28\u0D4D\u0D28\u0D46\u0020\u0D24\u0D33\u0D4D\u0D33\u0D42";

        // Embed entire font without any subsetting. Please note that without subset it's impossible to edit a form field
        // with the predefined font
        font.setSubset(false);

        Rectangle rectangle = new Rectangle(50, 650, 100, 25);

        // Create a button for the form field, set its font and size
        PdfButtonFormField pushButton = PdfFormField.createPushButton(pdfDocument, rectangle, "Name", text);
        pushButton
                .setFont(font)
                .setFontSize(10);

        // Add the button to the form
        form.addField(pushButton);

        pdfDocument.close();
    }
}



