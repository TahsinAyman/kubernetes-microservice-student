package com.fullstackbd.tahsin.student.repository;

import com.fullstackbd.tahsin.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findAllBySchoolId(Long schoolId);
}
