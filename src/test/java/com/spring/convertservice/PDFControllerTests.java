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

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;

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

        String extension = "docx";
        String dot = ".";

        MockMultipartFile multipartFile = new MockMultipartFile(
                "file",
                "file" + dot + extension,
                String.valueOf(MediaType.MULTIPART_FORM_DATA),
                new ByteArrayInputStream("abc".getBytes(StandardCharsets.UTF_8)));

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
        assertThat(MediaType.APPLICATION_PDF.toString()).isEqualTo(result.getResponse().getContentType());
    }

    @Test
    public void PptxToPDFControllerTest() throws Exception {
        String endpoint = "/api/v1/fileToPDF";

        String extension = "pptx";
        String dot = ".";

        FileInputStream fis = new FileInputStream("src\\test\\static\\pptx\\1" + dot + extension);

        MockMultipartFile multipartFile = new MockMultipartFile(
                "file",
                "file" + dot + extension,
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
        assertThat(MediaType.APPLICATION_PDF.toString()).isEqualTo(result.getResponse().getContentType());
    }

    @Test
    public void JpgToPDFControllerTest() throws Exception {
        String endpoint = "/api/v1/fileToPDF";

        String extension = "jpg";
        String dot = ".";

        FileInputStream fis = new FileInputStream("src\\test\\static\\image\\1" + dot + extension);

        MockMultipartFile multipartFile = new MockMultipartFile(
                "file",
                "file" + dot + extension,
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
        assertThat(MediaType.APPLICATION_PDF.toString()).isEqualTo(result.getResponse().getContentType());
    }

    @Test
    public void JpegToPDFControllerTest() throws Exception {
        String endpoint = "/api/v1/fileToPDF";

        String extension = "jpeg";
        String dot = ".";

        FileInputStream fis = new FileInputStream("src\\test\\static\\image\\1" + dot + extension);

        MockMultipartFile multipartFile = new MockMultipartFile(
                "file",
                "file" + dot + extension,
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
        assertThat(MediaType.APPLICATION_PDF.toString()).isEqualTo(result.getResponse().getContentType());
    }

    @Test
    public void GifToPDFControllerTest() throws Exception {
        String endpoint = "/api/v1/fileToPDF";

        String extension = "gif";
        String dot = ".";

        FileInputStream fis = new FileInputStream("src\\test\\static\\image\\1" + dot + extension);

        MockMultipartFile multipartFile = new MockMultipartFile(
                "file",
                "file" + dot + extension,
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
        assertThat(MediaType.APPLICATION_PDF.toString()).isEqualTo(result.getResponse().getContentType());
    }

    @Test
    public void PngToPDFControllerTest() throws Exception {
        String endpoint = "/api/v1/fileToPDF";

        String extension = "png";
        String dot = ".";

        FileInputStream fis = new FileInputStream("src\\test\\static\\image\\1" + dot + extension);

        MockMultipartFile multipartFile = new MockMultipartFile(
                "file",
                "file" + dot + extension,
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
        assertThat(MediaType.APPLICATION_PDF.toString()).isEqualTo(result.getResponse().getContentType());
    }
}