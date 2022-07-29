package com.spring.convertservice.services;

import org.apache.commons.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.*;

public class PdfConvertService {

    public OutputStream generatePDFFromImage(byte[] array, String name) throws IOException {
        PDPageContentStream contents;
        PDDocument doc = new PDDocument();

        PDImageXObject pdImage = PDImageXObject.createFromByteArray(doc, array, name);

        PDPage page = new PDPage();
        doc.addPage(page);

        contents = new PDPageContentStream(doc, page);
        contents.drawImage(pdImage, 0.0F, 0.0F, pdImage.getWidth(), pdImage.getHeight());
        contents.close();

        OutputStream outputStream = new ByteArrayOutputStream();

        doc.save(outputStream);

        outputStream.close();
        doc.close();

        return outputStream;
    }
}
