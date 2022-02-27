package com.ddos.java.service;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;

@Service
public class HttpService {
    public int getSiteStatus(String urlString){
        try {
            HttpGet request = new HttpGet(urlString);
            CloseableHttpClient client = HttpClients.createDefault();
            CloseableHttpResponse response = client.execute(request);
            //HttpEntity entity = response.getEntity();
            //String result = EntityUtils.toString(entity);
            return response.getStatusLine().getStatusCode();
        } catch (Exception ignored){}
        return 0;
    }
}
