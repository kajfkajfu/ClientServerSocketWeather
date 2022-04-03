package service;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Thermometer {

    private String getUrlContent(String urlAddress) {
        StringBuffer content = new StringBuffer();

        try {
            URL url = new URL(urlAddress);
            URLConnection urlConn = url.openConnection();

            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            String line;

            while ((line = reader.readLine()) != null) {
                content.append(line + "\n");
            }
            reader.close();

        } catch (IOException e) {
            System.out.println("This city doesn't exist");
        }
        return content.toString();
    }

    public String getTemperature(String cityName) {
        String output = getUrlContent("https://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&appid=407f99176c3b4bc6d5194d86bf235b4f");
        if (!output.isEmpty()) {
            JSONObject ob = new JSONObject(output);
            output = String.format("%.1f", (ob.getJSONObject("main").getDouble("temp") - 273));
        }
        return output;
    }
}
