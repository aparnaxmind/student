package com.student.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Teacher {
    @Id
//    @SequenceGenerator(
//            name ="teacher_sequence",
//            sequenceName="teacher_sequence",
//            allocationSize = 2
//    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "teacher_sequence"
    )
    private Long  teacherId;
    private String name;
    private String email;
    private LocalDate dob;
    private Long contactNumber;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "teacher")
    private List<Course> course;
}
