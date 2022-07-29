package com.spring.convertservice.services;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootTest
class PdfConvertServiceTest {

    PdfConvertService convertService = new PdfConvertService();

    @Test
    public void testGeneratingPDFFromImage() throws Exception {
        String name = "1";

        Path imagePath = Paths.get("src/test/static/image/" + name + ".jpg");

        InputStream image = Files.newInputStream(imagePath);

        File resultFile = File.createTempFile("Test",".pdf");
        ByteArrayOutputStream byteArrayOutputStream = (ByteArrayOutputStream) convertService.generatePDFFromImage(IOUtils.toByteArray(image), name);
        try(OutputStream outputStream = new FileOutputStream(resultFile)) {
            byteArrayOutputStream.writeTo(outputStream);
        }

        System.out.println("Please find your PDF File here: " + resultFile.getAbsolutePath());
    }
}