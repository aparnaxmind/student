package com.student.demo.service;
import com.student.demo.Exception.StudentNotFoundException;
import com.student.demo.dtos.StudentDetailsRequestDTO;
import com.student.demo.entity.StudentProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import com.student.demo.entity.Student;
import com.student.demo.repository.StudentRepository;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

//    public List<com.student.demo.entity.Student> getStudents(){
//       // return studentRepository.findAll();
//        public List<StudentDetailsRequestDTO> listOfStudent(){
//            List<Student> listOfStudent=studentRepository.findAll();
//            List<StudentDetailsRequestDTO> studentDTOS=new ArrayList<>();
//            listOfStudent.forEach(detail->{
//                StudentDetailsRequestDTO stdnt =new StudentDetailsRequestDTO();
//                stdnt.setName(detail.getName());
//                stdnt.setEmail(detail.getEmail());
//                stdnt.setDob(detail.getDob());
//                stdnt.setAge(detail.getAge());
//                stdnt.setId(detail.getId());
//                studentDTOS.add(stdnt);
//            });
//            return studentDTOS;
//        }
//    }

    public List<StudentDetailsRequestDTO> getStudents(){
        // return studentRepository.findAll();

        List<Student> listOfStudent=studentRepository.findAll();
        List<StudentDetailsRequestDTO> studentDTOS=new ArrayList<>();
        listOfStudent.forEach(detail->{
            StudentDetailsRequestDTO stdnt =new StudentDetailsRequestDTO();
            stdnt.setName(detail.getName());
            stdnt.setEmail(detail.getEmail());
            stdnt.setDob(detail.getDob());
           stdnt.setAge(detail.getAge());
            stdnt.setStudentId(detail.getId());
            studentDTOS.add(stdnt);
        });
        return studentDTOS;

    }



    public void addNewStudent(StudentDetailsRequestDTO studentDetails) {
        Student student =new Student();
        student.setEmail(studentDetails.getEmail());
        student.setName(studentDetails.getName());
        student.setDob(studentDetails.getDob());
        StudentProfile studentProfile=new StudentProfile();
        studentProfile.setState(studentDetails.getState());
        studentProfile.setCity(studentDetails.getCity());
        studentProfile.setStreet(studentDetails.getStreet());
        studentProfile.setPincode(studentDetails.getPincode());
        studentProfile.setPhoneNumber(studentDetails.getPhoneNumber());
        studentProfile.setStudent(student);
        student.setStudent_profile(studentProfile);
        Optional<com.student.demo.entity.Student> studentOptional= studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()){
            throw new IllegalMonitorStateException("email taken");
        }
        studentRepository.save(student);
    }
    public Optional<Student> getStudents(Long id){
        return studentRepository.findById(id);
    }
    public void deleteStudent(Long studentId) {
        boolean exists= studentRepository.existsById(studentId);
        if(! exists){throw new IllegalStateException("student with id " + studentId +"does not exists");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email){
        com.student.demo.entity.Student student= studentRepository.findById(studentId).orElseThrow(()->new IllegalStateException("student with id " + studentId +"does not exists"));

        if (name !=null && name.length()>0 && !Objects.equals(student.getName(),name)){
            student.setName(name);
        }
        if (email !=null && email.length()>0 && !Objects.equals(student.getEmail(),email)){
            Optional<com.student.demo.entity.Student> studentOptional= studentRepository.findStudentByEmail(student.getEmail());
            if (studentOptional.isPresent()){
                throw new IllegalMonitorStateException("email taken");
            }
            student.setEmail(email);
        }
    }


}
