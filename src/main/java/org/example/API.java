package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

public class API {
    private int categoryFromUser;
    private int numberOfQuestions;


    public API (){

        showCategories();
        setCategoryFromUser();
        setNumberOfQuestions();

        String APIlink = linkFormatter(categoryFromUser, numberOfQuestions);

        startAsking(APIlink);

    }
    private void startAsking (String link){

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

            String jsonResponse = response.toString();

            ObjectMapper mapper = new ObjectMapper();
            TriviaResponse triviaResponse = mapper.readValue(jsonResponse, TriviaResponse.class);

            for (Questions question : triviaResponse.getResults()) {
                question.printQuestion();
            }

            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showCategories (){

        try {
            String apiUrl = "https://opentdb.com/api_category.php";

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

            String jsonResponse = response.toString();

            ObjectMapper mapper = new ObjectMapper();
            CategoryList categoryList = mapper.readValue(jsonResponse, CategoryList.class);

            List<org.example.Category> categories = categoryList.getCategories();

            for (Category category : categories) {
                System.out.println(category.getId() + ": " + category.getName());
            }

            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setCategoryFromUser() {
        System.out.println("Chose a category: ");
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        this.categoryFromUser = input;
    }

    public void setNumberOfQuestions() {

        System.out.println("How many questions do you want to have 10 - 50 ?");
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        this.numberOfQuestions = input;
    }

    private String linkFormatter (int category, int numberOfQuestions){

        String link = "https://opentdb.com/api.php?amount="+numberOfQuestions+"&category=" + category;
        return link;
    }
}
