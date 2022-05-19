package com.academic.ISSProject.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
public class GradeDto {

    private int grade;
    @DateTimeFormat(pattern = "dd-MM-yyyy@HH:mm:ss")
    @JsonFormat(pattern = "dd-MM-yyyy@HH:mm:ss")
    private Date receivedDate;
}
