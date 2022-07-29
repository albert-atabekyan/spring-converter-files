package com.spring.convertservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(path = "api/v1/fileToPDF")
public class PdfConvertController {

    @PostMapping("/")
    public ResponseEntity<?> convertPDFFileEndpoint(@RequestParam MultipartFile file) {

        return ResponseEntity.ok().build();
    }
}
