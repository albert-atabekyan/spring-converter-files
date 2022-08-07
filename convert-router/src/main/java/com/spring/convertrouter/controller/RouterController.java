package com.spring.convertrouter.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(path = "api/v1/convertFile")
public class RouterController {

    @PostMapping()
    public ResponseEntity<?> convertPDFFileEndpoint(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok("Test");
    }
}
