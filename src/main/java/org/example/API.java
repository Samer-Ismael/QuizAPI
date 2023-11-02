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

    private void showCategories (){

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

    private void setCategoryFromUser() {
        System.out.println("Choose a category (9 - 32): ");
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                int input = scanner.nextInt();
                if (input >= 9 && input <= 32) {
                    this.categoryFromUser = input;
                    break;
                } else {
                    System.out.println("Try again! Category must be between 9 and 32.");
                }
            } catch (Exception e) {
                System.out.println("Try again! Category must be between 9 and 32.");
            }
        }
    }

    private void setNumberOfQuestions() {

        System.out.println("How many questions do you want to have 10 - 50 ?");
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                int input = scanner.nextInt();
                if (input >= 10 && input <= 50) {
                    this.numberOfQuestions = input;
                    break;
                } else {
                    System.out.println("Try again!");
                }
            } catch (Exception e) {
                System.out.println("Try again!");
            }
        }
    }

    private String linkFormatter (int category, int numberOfQuestions){

        String link = "https://opentdb.com/api.php?amount="+numberOfQuestions+"&category=" + category;
        return link;
    }
}
