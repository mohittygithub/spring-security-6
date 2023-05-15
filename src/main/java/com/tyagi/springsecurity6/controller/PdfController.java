package com.tyagi.springsecurity6.controller;

import com.lowagie.text.DocumentException;
import com.tyagi.springsecurity6.dto.ApiResponse;
import com.tyagi.springsecurity6.service.PdfService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.UUID;

@SuppressWarnings("unused")
@RestController
@CrossOrigin
public class PdfController {
    private final PdfService pdfService;

    public PdfController(PdfService pdfService) {
        this.pdfService = pdfService;
    }

    @GetMapping("/export-to-pdf/{userId}")
    public ApiResponse generatePdfFile(@PathVariable UUID userId, HttpServletResponse response) throws DocumentException, IOException {
        return pdfService.generate(userId, response);

    }
}
