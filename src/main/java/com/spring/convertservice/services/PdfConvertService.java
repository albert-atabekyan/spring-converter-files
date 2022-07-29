package com.spring.convertservice.services;

import org.apache.commons.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.*;
import java.nio.file.Files;

public class PdfConvertService {

    public OutputStream generatePDFFromImage(File file) throws IOException {
        PDPageContentStream contents;
        PDDocument doc = new PDDocument();

        PDImageXObject pdImage = PDImageXObject.createFromByteArray(doc, Files.readAllBytes(file.toPath()), file.getName());

        PDPage page = new PDPage();
        doc.addPage(page);

        contents = new PDPageContentStream(doc, page);
        contents.drawImage(pdImage, 0.0F, 0.0F, pdImage.getWidth(), pdImage.getHeight());

        OutputStream outputStream = new ByteArrayOutputStream();

        doc.save(outputStream);

        doc.close();

        return outputStream;
    }
}
