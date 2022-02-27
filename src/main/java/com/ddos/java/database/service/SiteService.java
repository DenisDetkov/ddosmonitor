package com.ddos.java.database.service;

import com.ddos.java.database.entity.Site;
import com.ddos.java.database.repository.SiteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiteService extends DefaultService<Site, SiteRepository>{
    public List<Site> getByName(String name){return jpaRepository.getByName(name);}

    public List<Site> findAll(){return jpaRepository.findAll();}
}