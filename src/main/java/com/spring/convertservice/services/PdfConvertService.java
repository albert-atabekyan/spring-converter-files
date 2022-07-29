package com.spring.convertservice.services;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

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

    public OutputStream generatePDFFromDocx(InputStream is) throws IOException {
        try (OutputStream out = new ByteArrayOutputStream()) {
            XWPFDocument document = new XWPFDocument(is);

            PdfOptions options = PdfOptions.create();
            PdfConverter.getInstance().convert(document, out, options);

            return out;
        }
    }
}
