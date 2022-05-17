package com.academic.ISSProject.controller;


import com.academic.ISSProject.domain.Specialization;
import com.academic.ISSProject.domain.dto.SpecializationDto;
import com.academic.ISSProject.service.implementation.EnrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/enroll")
public class EnrollController {
    private final EnrollService enrollService;

    @Autowired
    public EnrollController(EnrollService enrollService) {
        this.enrollService = enrollService;
    }

    @GetMapping("/specializations")
    public List<SpecializationDto> getAllSpecializations(){
        return enrollService.getAllSpecializations();
    }
}
