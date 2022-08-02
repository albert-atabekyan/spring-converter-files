package com.spring.convertservice.controller;

import com.aspose.imaging.internal.bouncycastle.util.io.SimpleOutputStream;
import com.spring.convertservice.services.PdfConvertService;
import jdk.jfr.ContentType;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

@Controller
@RequestMapping(path = "api/v1/fileToPDF")
public class PdfConvertController {

    @PostMapping()
    public ResponseEntity<byte[]> convertPDFFileEndpoint(@RequestParam("file") MultipartFile file) throws Exception {
        PdfConvertService pdfConvertService = new PdfConvertService();

        String extension = FilenameUtils.getExtension(file.getOriginalFilename());

        List<String> availableExtensions = List.of("docx", "pptx", "jpg", "jpeg", "png", "gif");

        if(extension == null) throw new IllegalArgumentException("Null object");

        if(!availableExtensions.contains(extension)) throw new IllegalArgumentException("Not supported format" + extension);

        OutputStream outputStream;
        if(extension.equals("docx")) {
            outputStream = pdfConvertService.generatePDFFromDocx(file.getInputStream());
        } else if (extension.equals("pptx")) {
            outputStream = pdfConvertService.generatePDFFromPptx(file.getInputStream());
        } else {
            outputStream = pdfConvertService.generatePDFFromImage(file.getBytes(), file.getName());
        }

        File resultFile = File.createTempFile("Test",".pdf");

        ByteArrayOutputStream byteArrayOutputStream = (ByteArrayOutputStream) outputStream;
        try(OutputStream fileOutputStream = new FileOutputStream(resultFile)) {
            byteArrayOutputStream.writeTo(fileOutputStream);
        }

        return ResponseEntity.ok()
                .contentLength(resultFile.length())
                .contentType(MediaType.APPLICATION_PDF)
                .body(IOUtils.toByteArray(new FileInputStream(resultFile)));
    }
}
