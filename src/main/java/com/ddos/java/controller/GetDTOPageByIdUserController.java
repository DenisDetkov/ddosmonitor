package com.ddos.java.controller;


import com.ddos.java.database.service.DefaultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.lang.reflect.ParameterizedType;

public abstract class GetDTOPageByIdUserController<D, T extends DefaultService>{
    @Autowired
    public T service;

    @GetMapping("/{id}")
    public D getById(@PathVariable("id") long id){
        return (D) service.getById(id);
    }
}
