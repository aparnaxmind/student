package com.student.demo.service;
import com.student.demo.dtos.StudentDetailsRequestDTO;
import com.student.demo.entity.Student;
import com.student.demo.entity.StudentProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    private static com.student.demo.repository.StudentRepository studentRepository;

    @Autowired
    public StudentService(com.student.demo.repository.StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<com.student.demo.entity.Student> getStudents(){
        return studentRepository.findAll();
    }

//    public static void addNewStudent(Student student) {
//        Optional<com.student.demo.entity.Student> studentOptional= studentRepository.findStudentByEmail(student.getEmail());
//        if (studentOptional.isPresent()){
//            throw new IllegalMonitorStateException("email taken");
//        }
//        studentRepository.save(student);
//
//        Student.setStudentProfile(StudentProfile);
//
//        // Set parent reference(user) in child entity(studentProfile)
//        StudentProfile.setStudent(student);
//
//        // Save Parent Reference (which will save the child as well)
//        studentRepository.save(student);
//    }
//    public static void addNewStudent(Student studentDetails) {
//        Optional<com.student.demo.entity.Student> studentOptional= studentRepository.findStudentByEmail(Student.getEmail());
//        if (studentOptional.isPresent()){
//            throw new IllegalMonitorStateException("email taken");
//        }
//
//        Student student =new Student();
//        student.setEmail(studentDetails.getEmail());
//        student.setName(studentDetails.getName());
//
//        StudentProfile studentProfile =new StudentProfile();
//        studentProfile.setAddress(studentProfile.getAddress());
//        studentProfile.setPhonenumber(studentProfile.getPhonenumber());
//        studentProfile.setStudent(student);
//        student.setstudent_profile(studentProfile);
//        //student.setStudentProfile(studentProfile);
//        studentRepository.save(student);
//    }

    public static void addNewStudent(StudentDetailsRequestDTO studentDetails) {
        Student student =new Student();
        student.setEmail(studentDetails.getEmail());
        student.setName(studentDetails.getName());
        student.setDob(studentDetails.getDob());
        StudentProfile studentProfile=new StudentProfile();
        studentProfile.setAddress(studentProfile.getAddress());
        studentProfile.setPhonenumber(studentProfile.getPhonenumber());
        studentProfile.setStudent(student);
        student.setStudent_profile(studentProfile);
        Optional<com.student.demo.entity.Student> studentOptional= studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()){
            throw new IllegalMonitorStateException("email taken");
        }
        studentRepository.save(student);
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
