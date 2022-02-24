package com.student.demo.controller;
import com.student.demo.Exception.StudentNotFoundException;
import com.student.demo.dtos.StudentDetailsRequestDTO;
import com.student.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.student.demo.entity.Student;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="api/v1/student")
public class StudentController {
    private final StudentService studentService;
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

//    @GetMapping
//    public List<Student> getStudents(){
//        List<Student> studentList=studentService.getStudents();
//        if(studentList.isEmpty())
//            throw new StudentNotFoundException("Student list is empty");
//        return studentList;
//    }
    @GetMapping
    public List<StudentDetailsRequestDTO> getStudents(){
        List<StudentDetailsRequestDTO> studentList=studentService.getStudents();
        if(studentList.isEmpty())
            throw new StudentNotFoundException("Student list is empty");
        return studentList;
    }

    @GetMapping("/student/{id}")
    public Optional<Student> findstudentbyid(@PathVariable long id) {
        Optional<Student> student;
        student=studentService.getStudents(id);
        if(student.isPresent()){
            return student;
        }else
            throw new StudentNotFoundException("invalid id");
//        return student;
    }
    @PostMapping
    public void registerNewStudent(@RequestBody StudentDetailsRequestDTO studentDto){
        studentService.addNewStudent(studentDto);
    }
    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId")Long studentId){
        studentService.deleteStudent(studentId);
    }
    @PutMapping(path = "{studentId}")
    public void updateStudent(
            @PathVariable("studentId")Long studentId,
            @RequestParam(required = false)  String name,
            @RequestParam(required = false) String email){
        studentService.updateStudent(studentId,name,email);
    }



}


