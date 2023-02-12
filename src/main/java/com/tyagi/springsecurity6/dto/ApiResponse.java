package com.tyagi.springsecurity6.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class ApiResponse {

    private UUID recordId;
    private String message;
    private Boolean success;
    private Integer noOfRecords;
    private List<?> records = new ArrayList<>();
}
