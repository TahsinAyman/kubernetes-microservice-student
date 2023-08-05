package com.fullstackbd.tahsin.student.client;

import com.fullstackbd.tahsin.student.entity.School;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

@HttpExchange
public interface SchoolClient {
    @GetExchange("/school/{id}")
    School findById(@PathVariable("id") Long id);
}

