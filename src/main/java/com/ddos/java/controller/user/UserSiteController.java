package com.ddos.java.controller.user;

import com.ddos.java.database.entity.Site;
import com.ddos.java.database.service.SiteService;
import com.ddos.java.service.HttpService;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api/user/sites")
public class UserSiteController {
    @Autowired
    SiteService service;
    @Autowired
    HttpService httpService;

    @PostMapping(value = "/propose")
    public void propose(String url, HttpServletResponse response) throws IOException {
        Site site = new Site();
        site.setName(url);
        site.setUrl(url);
        service.save(site);
        response.sendRedirect("/");
    }
}
