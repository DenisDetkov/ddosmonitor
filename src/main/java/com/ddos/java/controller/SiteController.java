package com.ddos.java.controller;

import com.ddos.java.database.entity.Site;
import com.ddos.java.database.service.SiteService;
import com.ddos.java.service.HttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/sites")
public class SiteController {
    @Autowired
    SiteService service;
    @Autowired
    HttpService httpService;

    @PostMapping
    public void create(Site site){
        //site.setStatus(httpService.getSiteStatus(site.getUrl()));
        service.save(site);
    }


    @PutMapping
    public void update(Site lawSchool){
        create(lawSchool);
    }

    @GetMapping
    public List<Site> getPaginated(@RequestParam(required = false) String name){
        return service.getByName(name);
    }

    @PostMapping(value = "/{id}")
    public void delete(@PathVariable long id){
        service.deleteById(id);
    }
}
