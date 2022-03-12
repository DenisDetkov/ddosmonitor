package com.ddos.java.controller.global;

import com.ddos.java.database.dto.SiteDTO;
import com.ddos.java.database.entity.Site;
import com.ddos.java.database.service.SiteService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/global/api")
@Api(tags = "Sites Searching")
public class GlobalController {
    @Autowired
    SiteService siteService;
    @Autowired
    private ModelMapper mapper;

    @GetMapping(value = "sites")
    @Operation(summary = "get")
    public List<SiteDTO> getSitesByUrl(@RequestParam(required = false) @Parameter(description = "Url part for filtering") String url, @RequestParam(required = false) @Parameter(description = "Site online status (true or false)") String online){
        List<Site> sites;
        if (online == null || online.equals("")) sites = siteService.getByUrl(url);
        else sites = siteService.getByUrlAndOnline(url, online.equals("true"));

        if (sites.size() == 0) return null;
        else return sites.stream().map(e -> mapper.map(e, SiteDTO.class)).collect(Collectors.toList());
    }
}
