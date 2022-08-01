package com.spring.convertservice;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.spring.convertservice.controller.PdfConvertController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.FileInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootTest
public class PDFControllerTests {

    MockMvc mockMvc;

    String BASE_URL = "http://localhost";

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new PdfConvertController()).build();
    }

    @Test
    public void shouldReturnDefaultMessage() throws Exception {

        String endpoint = BASE_URL + "/api/v1/fileToPDF/";

        FileInputStream fis = new FileInputStream("src/test/static/image/1.jpg");
        System.out.println(fis);

        MockMultipartFile multipartFile = new MockMultipartFile("file", fis);

        MediaType mediaType = new MediaType("multipart", "form-data");

        mockMvc.perform(
                        MockMvcRequestBuilders.multipart(endpoint).file(multipartFile))
                .andExpect(status().isOk());
    }
}