package com.fullstackbd.tahsin.student.client;

import com.fullstackbd.tahsin.student.entity.School;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SchoolClientTest {

    @Autowired
    private SchoolClient client;

    @Test
    public void testFindById() {
        School school = client.findById(2L);
        System.out.println(school);
    }
}