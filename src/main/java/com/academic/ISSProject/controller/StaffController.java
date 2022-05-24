package com.academic.ISSProject.controller;


import com.academic.ISSProject.domain.Staff;
import com.academic.ISSProject.domain.dto.AverageDto;
import com.academic.ISSProject.domain.dto.ProfileDto;
import com.academic.ISSProject.domain.dto.StudentGradeDto;
import com.academic.ISSProject.domain.dto.UserInfoDto;
import com.academic.ISSProject.service.implementation.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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

    /**
     * Endpoint that receives the list with all staff members
     * @return List<Staff>
     */
    @GetMapping
    public List<Staff> getAll(){
        return this.staffService.findAll();
    }

    /**
     * Endpoint that returns the staff having the requested ID
     * @param staffId the staff's id
     * @return Staff object
     */
    @GetMapping("/{staffId}")
    public Staff getStaff(@PathVariable long staffId){
        Staff staff = staffService.findById(staffId);
        if(staff == null){
            throw new NoSuchElementException("The staff with id " + staffId + " was not found.\n");
        }
        return staff;
    }

    /**
     * Post endpoint that adds a new staff member
     * @param userInfoDto the staff data
     * @return the Staff object that was added
     */
    @PostMapping
    public Staff addStaff(@RequestBody UserInfoDto userInfoDto){
        return staffService.save(userInfoDto);
    }

    /**
     * Endpoint that updates a staff's profile
     * @param staffId staff's id
     * @param profileDto profile data to be updated
     * @return updated staff object
     */
    @PutMapping("/{staffId}/profile")
    public Staff updateProfile(@PathVariable Long staffId, @RequestBody ProfileDto profileDto , Principal principal){
        String username = principal.getName();

        return staffService.updateProfile(staffId, profileDto,username);
    }

    /**
     * Endpoint that deletes a staff
     * @param staffId staff's id
     * @return deleted staff
     */
    @DeleteMapping("/{staffId}")
    public Staff deleteStaff(@PathVariable long staffId){
        Staff staff = this.staffService.findById(staffId);

        if (staff == null){
            throw new NoSuchElementException("The staff with id " + staffId + " was not found.\n");
        }

        this.staffService.deleteById(staffId);
        return staff;
    }

    /**
     * Endpoint that returns an ordered list with students and their average grade
     * @return List<StudentGradeDto>
     */
    @GetMapping("/sorted-average")
    public List<StudentGradeDto> getAllStudentsSortedByAverage(){
        return this.staffService.getStudentsOrderedByResults();
    }

    /**
     * Endpoint that returns an ordered list with the students and their average grade
     * from the requested year
     * @param year the requested year
     * @return List<AverageDto>
     */
    @GetMapping("/sorted-average/year/{year}")
    public List<AverageDto> getAllStudentsSortedByAverageInYear(@PathVariable Long year){
        return staffService.getAllStudentsSortedByAverageInYear(year);
    }

    ///todo set teacher degree
}
