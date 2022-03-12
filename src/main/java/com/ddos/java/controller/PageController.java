package com.ddos.java.controller;

import com.ddos.java.database.entity.BannedIp;
import com.ddos.java.database.entity.Site;
import com.ddos.java.database.service.BannedIpService;
import com.ddos.java.database.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@ApiIgnore
public class PageController {
    @Autowired
    SiteService siteService;
    @Autowired
    BannedIpService bannedIpService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getMainPage(Model model) {
        model.addAttribute("sites", siteService.findAll());
        return "index";
    }

    @RequestMapping(value = "/error/ru", method = RequestMethod.GET)
    public String getErrorRusPage(Model model) {
        model.addAttribute("error", "RUSSIANS NOT ALLOWED HERE");
        return "error";
    }

    @RequestMapping(value = "/error/ban", method = RequestMethod.GET)
    public String getErrorBanPage(Model model) {
        model.addAttribute("error", "YOU HAVE BEEN BANNED. YOU CAN CONTACT US HERE putinxyilo156@gmail.com");
        return "error";
    }

    @RequestMapping(value = "/admin", method = {RequestMethod.GET, RequestMethod.POST})
    public String getAdminPage(Model model, String name) {
        model.addAttribute("emptySite", new Site());
        if (name != null) model.addAttribute("sites", siteService.getByName(name));
        return "admin";
    }

    @RequestMapping(value = "/admin/verifying", method = RequestMethod.GET)
    public String getVerifyingPage(Model model) {
        model.addAttribute("emptySite", new Site());
        model.addAttribute("sites", siteService.findAllUnverified());
        return "verify";
    }

    @RequestMapping(value = "/admin/ips", method = RequestMethod.GET)
    public String getIpsPage(Model model) {
        model.addAttribute("ips", bannedIpService.findAll());
        return "ips";
    }

    @RequestMapping(value = "/leaked", method = RequestMethod.GET)
    public String getLeakedPage(Model model) {
        return "leaked";
    }
}
