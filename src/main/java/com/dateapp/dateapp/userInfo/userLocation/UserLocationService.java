package com.dateapp.dateapp.userInfo.userLocation;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class UserLocationService {

    Location getUserLocationInfo(String name){
return null;
    }

    private void connectWithCitiesApi(String name) throws IOException {
        URL url = new URL("https://api.api-ninjas.com/v1/city?name=" + name);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("accept", "application/json");
        InputStream responseStream = connection.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(responseStream);
        System.out.println(root.path("fact").asText());
    }
}
