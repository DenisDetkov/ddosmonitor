package com.ddos.java.controller;

import com.ddos.java.database.entity.Site;
import com.ddos.java.database.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PageController {
    @Autowired
    SiteService siteService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getMainPage(Model model) {
        model.addAttribute("sites", siteService.findAll());
        return "index";
    }

    @RequestMapping(value = "/admin", method = {RequestMethod.GET, RequestMethod.POST})
    public String getAdminPage(Model model, String name) {
        model.addAttribute("emptySite", new Site());
        if (name != null) model.addAttribute("sites", siteService.getByName(name));
        return "admin";
    }
}
