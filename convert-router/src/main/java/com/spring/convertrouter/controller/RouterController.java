package com.spring.convertrouter.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

@RestController
@RequestMapping(path = "api/v1/convertFile")
public class RouterController {

    @PostMapping("/{extension}")
    public ResponseEntity<?> convertPDFFileEndpoint(@PathVariable String extension,
                                                    @RequestParam("file") MultipartFile file
                                                    ) {
        String[] extensions = {"gif", "jpeg", "jpg", "png", "docx", "pptx"};

        if(!Arrays.asList(extensions).contains(extension))
            throw new IllegalArgumentException("Not contain available extension");

        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, Object> multiValueMap =
                new LinkedMultiValueMap<>();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        multiValueMap.add("file", file.getResource());

        HttpEntity<MultiValueMap<String, Object>> multiValueMapHttpEntity
                = new HttpEntity<>(multiValueMap, headers);


        ResponseEntity<byte[]> responseEntity =
                restTemplate.postForEntity("http://localhost:8082/api/v1/fileToPDF"
                        , multiValueMapHttpEntity
                        , byte[].class);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(responseEntity.getBody());
    }
}
