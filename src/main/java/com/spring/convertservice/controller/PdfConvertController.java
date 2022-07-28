package com.spring.convertservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "api/v1/fileToPDF")
public class PdfConvertController {

    @PostMapping("/")
    public ResponseEntity<?> convertPDFFileEndpoint() {

        return ResponseEntity.ok().build();
    }
}
