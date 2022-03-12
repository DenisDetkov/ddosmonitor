package com.ddos.java.config.filter;

import com.ddos.java.service.HttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(2)
public class CountryFilter implements Filter {
    @Autowired
    HttpService httpService;

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        try {
            String ip = request.getRemoteAddr();
            HttpServletRequest req = (HttpServletRequest) request;
            if (ip == null) req.getHeader(("X-Forwarded-For"));
            String countryCode = httpService.getIpInfo(ip).getCountryCode();
            if (countryCode.equals("RU") && !req.getRequestURI().equals("/error/ru") && !req.getRequestURI().equals("/error/ban")) {
                HttpServletResponse resp = (HttpServletResponse) response;
                resp.sendRedirect("/error/ru");
                return;
            }
        } catch (Exception ignored){}
        chain.doFilter(request, response);
    }
}
