package com.ddos.java.controller;

import com.ddos.java.database.service.BannedIpService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api/admin/bannedIps")
@ApiIgnore
public class BannedIpController {
    @Autowired
    BannedIpService service;

    @PostMapping(value = "/{id}")
    public void delete(@PathVariable long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        service.deleteById(id);
        response.sendRedirect(request.getHeader("referer"));
    }
}
