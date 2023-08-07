package com.fullstackbd.tahsin.student.controller;

import com.fullstackbd.tahsin.student.entity.Message;
import com.fullstackbd.tahsin.student.entity.Student;
import com.fullstackbd.tahsin.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.ConnectException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService service;

    @GetMapping
    public List<Student> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id){
        Student foundStudent = service.findById(id);
        if (foundStudent != null) {
            return ResponseEntity.status(HttpStatus.OK).body(foundStudent);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Message
                        .builder()
                        .message("Student not found")
                        .result(false)
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .build()
        );
    }

//    @PostMapping
//    public ResponseEntity<?> save(@RequestBody Student student){
//        Student savedStudent = null;
//        try {
//            savedStudent = service.save(student);
//        } catch (ConnectException e) {
//            System.out.println(e);
//            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(
//                Message
//                        .builder()
//                        .message("Service Unavailable")
//                        .result(false)
//                        .statusCode(HttpStatus.SERVICE_UNAVAILABLE.value())
//                        .build()
//            );
//        }
//        if (savedStudent != null) {
//            return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
//        }
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                Message
//                        .builder()
//                        .message("School not found")
//                        .result(false)
//                        .statusCode(HttpStatus.NOT_FOUND.value())
//                        .build()
//        );
//    }

    @PostMapping
    public Student save(@RequestBody Student student){
        return service.save(student);
    }

    @GetMapping("/school/{id}")
    public List<Student> findBySchoolId(@PathVariable("id") Long id){
        return service.findBySchoolId(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Student student){
        Student updatedStudent = service.save(id, student);
        if (updatedStudent != null) {
            return ResponseEntity.status(HttpStatus.OK).body(updatedStudent);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Message
                        .builder()
                        .message("Student not found")
                        .result(false)
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .build()
        );
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id){
        Student deletedStudent = service.deleteById(id);
        if (deletedStudent != null) {
            return ResponseEntity.status(HttpStatus.OK).body(deletedStudent);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Message
                        .builder()
                        .message("Student not found")
                        .result(false)
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .build()
        );
    }
}
