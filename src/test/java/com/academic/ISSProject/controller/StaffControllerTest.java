package com.academic.ISSProject.controller;

import com.academic.ISSProject.domain.Staff;
import com.academic.ISSProject.domain.Teacher;
import com.academic.ISSProject.domain.dto.UserInfoDto;
import com.academic.ISSProject.service.implementation.StaffService;
import com.academic.ISSProject.service.implementation.TeacherService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StaffControllerTest {

    @Mock
    private StaffService staffService;
    @InjectMocks
    private StaffController testController;

    @Test
    void getAll() {
        List<Staff> list = new ArrayList<>();
        Staff staff = new Staff();
        staff.setId(1L);

        Staff staff1 = new Staff();
        staff1.setId(2L);

        list.add(staff);
        list.add(staff1);

        when(staffService.findAll()).thenReturn(list);
        assertEquals(testController.getAll(),list);
    }

    @Test
    void getStaff() {
        Staff staff = new Staff();
        staff.setId(1L);

        when(staffService.findById(1L)).thenReturn(staff);
        assertEquals(testController.getStaff(1L), staff);
    }

    @Test
    void addStaff() {
        Staff staff = new Staff();
        staff.setId(1L);

        when(staffService.save(any(UserInfoDto.class))).thenReturn(staff);
        assertEquals(testController.addStaff(new UserInfoDto()), staff);
    }

    @Test
    void updateProfile() {
    }

    @Test
    void deleteStaff() {
    }

    @Test
    void getAllStudentsSortedByAverage() {
    }

    @Test
    void getAllStudentsSortedByAverageInYear() {
    }
}