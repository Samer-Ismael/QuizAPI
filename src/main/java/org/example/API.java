package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class API {

    public API (String link){

        try {
            String apiUrl = link;

            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Parse the JSON response
            String jsonResponse = response.toString();

            // Format and print the JSON response
            ObjectMapper mapper = new ObjectMapper();
            TriviaResponse triviaResponse = mapper.readValue(jsonResponse, TriviaResponse.class);

            // Print the questions nicely
            for (Questions question : triviaResponse.getResults()) {
                question.printQuestion();
            }

            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
