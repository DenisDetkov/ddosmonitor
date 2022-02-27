package com.ddos.java.controller;

import com.ddos.java.database.service.DefaultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@PreAuthorize("hasRole('ADMIN')")
public abstract class CRUDController<E, T extends DefaultService> {
    @Autowired
    public T service;

    @PostMapping
    public void create(@RequestBody E e){
        service.save(e);
    }

    @PutMapping
    public void update(@RequestBody E e){
        service.save(e);
    }

    @DeleteMapping
    public void delete(@RequestParam long id){
        service.deleteById(id);
    }
}
