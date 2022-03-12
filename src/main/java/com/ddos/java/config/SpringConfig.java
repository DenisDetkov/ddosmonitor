package com.ddos.java.config;

import com.ddos.java.database.entity.Site;
import com.ddos.java.database.service.SiteService;
import com.ddos.java.service.CheckHostService;
import com.ddos.java.service.HttpService;
import io.ipinfo.api.model.IPResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableScheduling
public class SpringConfig {
    @Autowired
    SiteService siteService;
    @Autowired
    HttpService httpService;
    @Autowired
    CheckHostService checkHostService;

    ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(20);

    @Scheduled(fixedDelay = 7 * 60000)
    public void scheduleFixedDelayTask() {
        System.out.println("Checking - " + executor.getActiveCount());
        if (executor.getActiveCount() > 0) return;

        System.out.println("Started");
        for (Site site : siteService.findAllCanPing()) {
            executor.submit(() -> {
                boolean isOnline = checkHostService.isSiteOnline(site.getUrl());
                String siteIp = httpService.getSiteIp(site.getUrl());
                isOnline = isOnline && !siteIp.equals("127.0.0.1");
                String siteLocation = null;
                if (site.getLocation() == null) {
                    IPResponse ipResponse = httpService.getIpInfo(siteIp);
                    if (ipResponse != null && ipResponse.getCountryCode() != null && ipResponse.getRegion() != null) siteLocation = ipResponse.getCountryCode() + " - " + ipResponse.getRegion();
                } else siteLocation = site.getLocation();
                System.out.println("Site - " + site.getUrl() + " " + isOnline + " Ip - " + siteIp + " Location - " + siteLocation);
                if (site.isOnline() != isOnline || !Objects.equals(site.getIp(), siteIp) || !Objects.equals(site.getLocation(), siteLocation)) {
                    site.setOnline(isOnline);
                    site.setIp(siteIp);
                    site.setLocation(siteLocation);
                    siteService.save(site);
                }
                return null;
            });
        }
        System.out.println("Stopped");
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
