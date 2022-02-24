package com.student.demo.controller;

import com.student.demo.Exception.StudentNotFoundException;
import com.student.demo.dtos.StudentDetailsRequestDTO;
import com.student.demo.dtos.TeacherDetailsRequestDTO;
import com.student.demo.entity.Student;
import com.student.demo.entity.Teacher;
import com.student.demo.service.StudentService;
import com.student.demo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="api/v1/teacher")


public class TeacherController {
    private final TeacherService teacherService;
    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public List<TeacherDetailsRequestDTO> getTeacher(){
        List<TeacherDetailsRequestDTO> teacherList=teacherService.getTeacher();
        if(teacherList.isEmpty())
            throw new StudentNotFoundException("Teacher list is empty");
        return teacherList;
    }

    @GetMapping("/teacher/{id}")
    public Optional<Teacher> findstudentbyid(@PathVariable long id) {
        Optional<Teacher> teacher;
        teacher=teacherService.getTeacher(id);
        if(teacher.isPresent()){
            return teacher;
        }else
            throw new StudentNotFoundException("invalid id");

    }
    @PostMapping
    public void registerNewTeacher(@RequestBody TeacherDetailsRequestDTO teacherDto){
        teacherService.addNewTeacher(teacherDto);
    }
    @DeleteMapping(path = "{teacherId}")
    public void deleteTeacher(@PathVariable("teacherId")Long studentId){
        teacherService.deleteTeacher(studentId);
    }
    @PutMapping(path = "{teacherId}")
    public void updateTeacher(
            @PathVariable("studentId")Long teacherId,
            @RequestParam(required = false)  String name,
            @RequestParam(required = false) String email){
        teacherService.updateTeacher(teacherId,name,email);
    }
}
