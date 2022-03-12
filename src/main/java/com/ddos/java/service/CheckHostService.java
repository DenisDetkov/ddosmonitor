package com.ddos.java.service;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service
public class CheckHostService {

    public boolean isSiteOnline(String urlString) {
        try {
            String russianNodeName = "ru1.node.check-host.net";

            List<NameValuePair> parameters = new ArrayList<>();
            parameters.add(new BasicNameValuePair("host", urlString));
            parameters.add(new BasicNameValuePair("max_nodes", "1"));
            parameters.add(new BasicNameValuePair("node", russianNodeName));

            JSONObject firstRequest = getResponse("https://check-host.net/check-http", parameters);
            JSONObject secondRequest;

            for (int i = 0; i < 7; i++) {
                Thread.sleep(4000);
                secondRequest = getResponse("https://check-host.net/check-result/" + firstRequest.get("request_id").toString(), new ArrayList<>());
                if (secondRequest.get(russianNodeName) != null) {
                    JSONArray obj = (JSONArray) ((JSONArray) secondRequest.get(russianNodeName)).get(0);
                    return obj.get(0).toString().equals("1");
                }
            }

        } catch (Exception ignored) {
        }
        return false;
    }

    private JSONObject getResponse(String url, List<NameValuePair> parameters) {
        try {
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet(url);
            request.setHeader("Accept", "application/json");
            URI uri = new URIBuilder(request.getURI()).addParameters(parameters).build();
            request.setURI(uri);

            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            String responseString = EntityUtils.toString(entity, "UTF-8");
            return (JSONObject) new JSONParser().parse(responseString);
        } catch (Exception ignored) {
        }
        return new JSONObject();
    }
}
