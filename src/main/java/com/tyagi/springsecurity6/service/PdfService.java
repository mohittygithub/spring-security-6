package com.tyagi.springsecurity6.service;

import com.lowagie.text.DocumentException;
import com.tyagi.springsecurity6.dto.ApiResponse;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

public interface PdfService {

    ApiResponse generate(UUID userId, HttpServletResponse response) throws DocumentException, IOException;
}
