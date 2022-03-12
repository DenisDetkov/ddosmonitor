package com.ddos.java.config.filter;

import com.ddos.java.database.entity.BannedIp;
import com.ddos.java.database.service.BannedIpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Order(1)
public class DDoSFilter implements Filter {
    @Autowired
    BannedIpService bannedIpService;

    public static Map<BannedIp, Integer> ipsConnected = new HashMap<>();
    public static List<BannedIp> ipsBanned = new ArrayList<>();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            HttpServletRequest req = (HttpServletRequest) request;
            BannedIp ip = buildObject(req.getRemoteAddr());
            if (!req.getRequestURI().equals("/error/ban")) {
                if (ipsConnected.containsKey(ip)) {
                    if (ipsConnected.get(ip) >= 200) {
                        bannedIpService.save(ip);
                        ipsConnected.remove(ip);
                        ipsBanned.add(ip);
                    } else ipsConnected.put(ip, ipsConnected.get(ip) + 1);
                } else if (ipsBanned.contains(ip)) {
                    HttpServletResponse resp = (HttpServletResponse) response;
                    resp.sendRedirect("/error/ban");
                    return;
                } else ipsConnected.put(ip, 1);
            }
        } catch (Exception ignored){ }
        chain.doFilter(request, response);
    }

    @Scheduled(fixedDelay = 1 * 60000)
    public void update() {
        ipsBanned = bannedIpService.findAll();
       ipsConnected.clear();
    }

    public BannedIp buildObject(String ip){
        BannedIp bannedIp = new BannedIp();
        bannedIp.setIp(ip);
        return bannedIp;
    }
}
