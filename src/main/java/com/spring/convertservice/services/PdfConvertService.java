package com.spring.convertservice.services;

import org.apache.commons.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class PdfConvertService {

    private OutputStream generatePDFFromImage(InputStream inputStream, String name) throws IOException {
        PDPageContentStream contents;
        PDDocument doc = new PDDocument();

        PDImageXObject pdImage = PDImageXObject.createFromByteArray(doc, IOUtils.toByteArray(inputStream), name);

        PDPage page = new PDPage();
        doc.addPage(page);

        contents = new PDPageContentStream(doc, page);
        contents.drawImage(pdImage, 0.0F, 0.0F, pdImage.getWidth(), pdImage.getHeight());

        OutputStream outputStream = doc.getDocument().createCOSStream().createOutputStream();

        doc.close();

        return outputStream;
    }
}
