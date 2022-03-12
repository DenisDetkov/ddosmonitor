package com.ddos.java.database.service;

import com.ddos.java.database.entity.BannedIp;
import com.ddos.java.database.entity.Site;
import com.ddos.java.database.repository.BannedIpRepository;
import com.ddos.java.database.repository.SiteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BannedIpService extends DefaultService<BannedIp, BannedIpRepository>{
    public List<BannedIp> findAll(){return jpaRepository.findAll();}

    public BannedIp findByIp(String ip){return jpaRepository.findByIp(ip);}
}