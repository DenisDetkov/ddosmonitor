package com.ddos.java.config;

import com.ddos.java.database.entity.Site;
import com.ddos.java.database.service.SiteService;
import com.ddos.java.service.HttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class SpringConfig {
    @Autowired
    SiteService siteService;
    @Autowired
    HttpService httpService;

    @Scheduled(fixedDelay = 60000)
    public void scheduleFixedDelayTask() {
        for (Site site: siteService.findAll()){
            site.setStatus(httpService.getSiteStatus(site.getUrl()));
            siteService.save(site);
        }
    }
}
