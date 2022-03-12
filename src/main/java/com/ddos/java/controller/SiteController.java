package com.ddos.java.controller;

import com.ddos.java.database.entity.Site;
import com.ddos.java.database.service.SiteService;
import com.ddos.java.service.HttpService;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/admin/sites")
@ApiIgnore
public class SiteController {
    @Autowired
    SiteService service;
    @Autowired
    HttpService httpService;

    @PostMapping
    public void create(Site site, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (service.getByUrl(site.getUrl()).size() == 0) {
            site.setVerified(true);
            service.save(site);
        }
        response.sendRedirect(request.getHeader("referer"));
    }

    @PutMapping
    public void update(Site site, HttpServletRequest request, HttpServletResponse response) throws IOException {
        service.save(site);
        response.sendRedirect(request.getHeader("referer"));
    }

    @GetMapping
    public List<Site> getPaginated(@RequestParam(required = false) String name) {
        return service.getByName(name);
    }

    @PostMapping(value = "/{id}")
    public void delete(@PathVariable long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        service.deleteById(id);
        response.sendRedirect(request.getHeader("referer"));
    }
}
