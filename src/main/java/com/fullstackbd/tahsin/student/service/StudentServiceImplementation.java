package com.fullstackbd.tahsin.student.service;

import com.fullstackbd.tahsin.common.events.SchoolExistenceCheckEvent;
import com.fullstackbd.tahsin.student.client.SchoolClient;
import com.fullstackbd.tahsin.student.entity.Student;
import com.fullstackbd.tahsin.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.net.ConnectException;
import java.util.List;

@Service
public class StudentServiceImplementation implements StudentService {
    @Autowired
    private StudentRepository repository;
    @Autowired
    private SchoolClient schoolClient;
    @Autowired
    private SchoolExistenceCheckProducer schoolExistenceCheckProducer;

    @Override
    public List<Student> findAll() {
        return repository.findAll();
    }

    @Override
    public Student findById(Long id) {
        return repository.findById(id).orElse(null);
    }

//    @Override
//    public Student save(Student student) throws ConnectException {
//        try {
//            schoolClient.findById(student.getSchoolId());
//            return repository.save(student);
//        } catch (WebClientResponseException e) {
//            return null;
//        } catch (Exception e) {
//            throw new ConnectException("Service Unavailable");
//        }
//    }
    @Override
    public Student save(Student student) {
        Student savedStudent = repository.save(student);
        schoolExistenceCheckProducer.produce(
                SchoolExistenceCheckEvent
                        .builder()
                        .studentId(savedStudent.getId())
                        .schoolId(savedStudent.getSchoolId())
                        .build()
        );
        return savedStudent;
    }


    @Override
    public Student save(Long id, Student student) {
        Student existingStudent = this.findById(id);
        if (existingStudent != null) {
            existingStudent.setName(student.getName());
            existingStudent.setAge(student.getAge());
            existingStudent.setSchoolId(student.getSchoolId());
            return repository.save(existingStudent);
        }
        return null;
    }

    @Override
    public Student deleteById(Long id) {
        Student existingStudent = this.findById(id);
        if (existingStudent != null) {
            repository.deleteById(id);
            return existingStudent;
        }
        return null;
    }

    @Override
    public List<Student> findBySchoolId(Long id) {
        return repository.findAllBySchoolId(id);
    }
}
