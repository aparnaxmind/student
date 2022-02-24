package com.student.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="course")
@Data
public class Course {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_sequence"
    )
    private Long courseid;
    private String coursename;
    private String duration;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="teacherId",referencedColumnName = "teacherId")
    private Teacher teacher;
}
