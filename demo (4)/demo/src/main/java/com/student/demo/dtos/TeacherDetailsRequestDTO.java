package com.student.demo.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TeacherDetailsRequestDTO {
    private Long  teacherId;
    private String name;
    private String email;
    private LocalDate dob;
    private Long contactNumber;
    private Long courseid;
    private String coursename;
    private String duration;
}
