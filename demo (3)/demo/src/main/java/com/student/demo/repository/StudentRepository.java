package com.student.demo.repository;

import com.student.demo.entity.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    //@Query("SELECT s from Student s WHERE s.email=?1")
    Optional<Student> findStudentByEmail(String email);

}


