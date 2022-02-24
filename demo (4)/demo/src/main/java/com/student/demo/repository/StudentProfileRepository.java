package com.student.demo.repository;

import com.student.demo.entity.StudentProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentProfileRepository extends JpaRepository<StudentProfile,Long> {
}
