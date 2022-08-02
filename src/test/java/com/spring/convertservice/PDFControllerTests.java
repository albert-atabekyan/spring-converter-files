package com.spring.convertservice;

import com.spring.convertservice.controller.PdfConvertController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.assertj.core.api.Assertions.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@SpringBootTest
public class PDFControllerTests {

    MockMvc mockMvc;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new PdfConvertController()).build();
    }

    @Test
    public void DocxToPDFControllerTest() throws Exception {

        String endpoint = "/api/v1/fileToPDF";

        InputStream fis = new FileInputStream(File.createTempFile("Test", "docx"));

        MockMultipartFile multipartFile = new MockMultipartFile(
                "file",
                "file.docx",
                String.valueOf(MediaType.MULTIPART_FORM_DATA),
                fis);

        HttpHeaders headers = new HttpHeaders();

        headers.add(HttpHeaders.ACCEPT, "*/*");
        headers.add(HttpHeaders.ACCEPT_ENCODING, "gzip, deflate, br");
        headers.add(HttpHeaders.CONNECTION, "keep-alive");

        MvcResult result = mockMvc.perform(
                        MockMvcRequestBuilders.multipart(endpoint)
                                .file(multipartFile).headers(headers)
                                .contentType(MediaType.MULTIPART_FORM_DATA))
                        .andReturn();

        assertThat(200).isEqualTo(result.getResponse().getStatus());
    }
}