package com.student.demo.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="student_profile")
@Data

public class StudentProfile {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
   Long id;
   String street;
   String city;
   String state;
   Long phoneNumber;
   Long pincode;
   @OneToOne(mappedBy="student_profile",fetch = FetchType.LAZY)
    private Student student;
}
