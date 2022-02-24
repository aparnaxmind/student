package com.student.demo.service;


import com.student.demo.dtos.StudentDetailsRequestDTO;
import com.student.demo.dtos.TeacherDetailsRequestDTO;
import com.student.demo.entity.Course;
import com.student.demo.entity.Student;
import com.student.demo.entity.StudentProfile;
import com.student.demo.entity.Teacher;
import com.student.demo.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;
    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public List<TeacherDetailsRequestDTO> getTeacher(){

        List<Teacher> listOfTeacher=teacherRepository.findAll();
        List<TeacherDetailsRequestDTO> teacherDTOS=new ArrayList<>();
        listOfTeacher.forEach(detail->{
            TeacherDetailsRequestDTO teachr =new TeacherDetailsRequestDTO();
            teachr.setTeacherId(detail.getTeacherId());
            teachr.setName(detail.getName());
            teachr.setEmail(detail.getEmail());
            teachr.setDob(detail.getDob());
            teachr.setContactNumber(detail.getContactNumber());
            teacherDTOS.add(teachr);
        });
        return teacherDTOS;

    }
    public void addNewTeacher(TeacherDetailsRequestDTO teacherDetails){
        Course course1 = new Course();
        course1.setCoursename(teacherDetails.getCoursename());
        Course course2 = new Course();
        course2.setCoursename(teacherDetails.getCoursename());
        Course course3 = new Course();
        course3.setCoursename(teacherDetails.getCoursename());
        List<Course> courseList = new ArrayList<>();
        courseList.add(course1);
        courseList.add(course2);
        courseList.add(course3);
        Teacher teacher = new Teacher();
        teacher.setEmail(teacherDetails.getEmail());
        teacher.setName(teacherDetails.getName());
        teacher.setDob(teacherDetails.getDob());
        teacher.setContactNumber(teacherDetails.getContactNumber());
        teacherRepository.save(teacher);





    }


//    public void addNewTeacher(TeacherDetailsRequestDTO teacherDetails) {
//        Teacher teacher =new Teacher();
//        teacher.setEmail(teacherDetails.getEmail());
//        teacher.setName(teacherDetails.getName());
//        teacher.setDob(teacherDetails.getDob());
//        teacher.setContactNumber(teacherDetails.getContactNumber());
//        Course course=new Course();
//        course.setCourseid(teacherDetails.getCourseid());
//        course.setCoursename(teacherDetails.getCoursename());
//        course.setDuration(teacherDetails.getDuration());
//
//        course.setTeacher(teacher);
//     //   teacher.setCourse(List<Course> course);
//        Optional<Teacher> teacherOptional= teacherRepository.findTeacherByEmail(teacher.getEmail());
//        if (teacherOptional.isPresent()){
//            throw new IllegalMonitorStateException("email taken");
//        }
//        teacherRepository.save(teacher);
//    }
    public Optional<Teacher> getTeacher(Long id){
        return teacherRepository.findById(id);
    }
    public void deleteTeacher(Long teacherId) {
        boolean exists= teacherRepository.existsById(teacherId);
        if(! exists){throw new IllegalStateException("teacher with id " + teacherId +"does not exists");
        }
        teacherRepository.deleteById(teacherId);
    }

    @Transactional
    public void updateTeacher(Long teacherId, String name, String email){
        com.student.demo.entity.Teacher teacher= teacherRepository.findById(teacherId).orElseThrow(()->new IllegalStateException("teacher with id " + teacherId +"does not exists"));

        if (name !=null && name.length()>0 && !Objects.equals(teacher.getName(),name)){
            teacher.setName(name);
        }
        if (email !=null && email.length()>0 && !Objects.equals(teacher.getEmail(),email)){
            Optional<com.student.demo.entity.Teacher> teacherOptional= teacherRepository.findTeacherByEmail(teacher.getEmail());
            if (teacherOptional.isPresent()){
                throw new IllegalMonitorStateException("email taken");
            }
            teacher.setEmail(email);
        }
    }


}
