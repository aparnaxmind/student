package com.student.demo.dtos;
import com.student.demo.entity.Student;
import com.student.demo.service.StudentService;
import lombok.Data;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;


@Data
public class StudentDetailsRequestDTO {
    private String name;
    private String email;
    private LocalDate dob;
    private String phonenumber;
    private String address;


}