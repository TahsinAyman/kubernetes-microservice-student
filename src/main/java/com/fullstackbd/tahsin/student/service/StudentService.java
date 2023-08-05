package com.fullstackbd.tahsin.student.service;

import com.fullstackbd.tahsin.student.entity.Student;

import java.net.ConnectException;
import java.util.List;

public interface StudentService {
    List<Student> findAll();
    Student findById(Long id);
    Student save(Student student) throws ConnectException;
    Student save(Long id, Student student);
    Student deleteById(Long id);

    List<Student> findBySchoolId(Long id);
}
