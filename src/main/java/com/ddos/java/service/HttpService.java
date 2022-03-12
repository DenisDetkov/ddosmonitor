package com.ddos.java.service;

import io.ipinfo.api.IPinfo;
import io.ipinfo.api.errors.RateLimitedException;
import io.ipinfo.api.model.IPResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.URL;

@Service
public class HttpService {
    @Value("${ipinfo.token}")
    private String ipInfoToken;

    public IPResponse getIpInfo(String ip) {
        IPinfo ipInfo = new IPinfo.Builder()
                .setToken(ipInfoToken)
                .build();
        try {
            return ipInfo.lookupIP(ip);
        } catch (RateLimitedException ignored) { }
        return null;
    }

    public String getSiteIp(String url) {
        try {
            InetAddress ip = InetAddress.getByName(new URL(url)
                    .getHost());

            return ip.getHostAddress();
        } catch (Exception ignored) {
        }
        return "";
    }
}
