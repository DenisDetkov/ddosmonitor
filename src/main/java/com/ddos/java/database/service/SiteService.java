package com.ddos.java.database.service;

import com.ddos.java.database.entity.Site;
import com.ddos.java.database.repository.SiteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiteService extends DefaultService<Site, SiteRepository>{
    public List<Site> getByName(String name){return jpaRepository.getByName(name);}

    public List<Site> getByUrl(String url){return jpaRepository.getByUrl(url);}

    public List<Site> getByUrlAndOnline(String url, boolean online){return jpaRepository.getByUrlAndOnline(url, online);}

    public List<Site> findAll(){return jpaRepository.findAll();}

    public List<Site> findAllCanPing(){return jpaRepository.findAllCanPing();}

    public List<Site> findAllUnverified(){return jpaRepository.findAllUnverified();}
}