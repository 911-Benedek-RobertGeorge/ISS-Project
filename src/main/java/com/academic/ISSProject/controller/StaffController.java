package com.academic.ISSProject.controller;


import com.academic.ISSProject.domain.Staff;
import com.academic.ISSProject.domain.dto.ProfileDto;
import com.academic.ISSProject.domain.dto.StudentGradeDto;
import com.academic.ISSProject.domain.dto.UserInfoDto;
import com.academic.ISSProject.service.implementation.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/staff")
public class StaffController {
    private final StaffService staffService;

    @Autowired
    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @GetMapping
    public List<Staff> getAll(){
        return this.staffService.findAll();
    }

    @GetMapping("/{staffId}")
    public Staff getStaff(@PathVariable long staffId){
        Staff staff = staffService.findById(staffId);
        if(staff == null){
            throw new NoSuchElementException("The staff with id " + staffId + " was not found.\n");
        }
        return staff;
    }

    @PostMapping
    public Staff addStaff(@RequestBody UserInfoDto userInfoDto){
        return staffService.save(userInfoDto);
    }

    @PutMapping("/{staffId}/profile")
    public Staff updateProfile(@PathVariable Long staffId, @RequestBody ProfileDto profileDto){
        return staffService.updateProfile(staffId, profileDto);
    }

    @DeleteMapping("/{staffId}")
    public Staff deleteStaff(@PathVariable long staffId){
        Staff staff = this.staffService.findById(staffId);

        if (staff == null){
            throw new NoSuchElementException("The staff with id " + staffId + " was not found.\n");
        }

        this.staffService.deleteById(staffId);
        return staff;
    }

    @GetMapping("/getSortedByAverage")
    public List<StudentGradeDto> getAllStudentsSortedByAverage(){
        return this.staffService.getStudentsOrderedByResults();
    }
}
