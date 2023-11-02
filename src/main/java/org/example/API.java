package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class API {
    private final ObjectMapper mapper = new ObjectMapper();
    private int categoryFromUser;
    private int numberOfQuestions;
    private String difficulty;


    public API() {

        showCategories();
        setCategoryFromUser();
        setNumberOfQuestions();
        setDifficulty();

        String APIlink = linkFormatter(categoryFromUser, numberOfQuestions, difficulty);

        startAsking(APIlink);

    }

    private String makeHttpRequest(String apiUrl) {
        try {
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

            connection.disconnect();
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void startAsking(String apiUrl) {
        try {
            String jsonResponse = makeHttpRequest(apiUrl);

            TriviaResponse triviaResponse = mapper.readValue(jsonResponse, TriviaResponse.class);

            for (Questions question : triviaResponse.getResults()) {
                question.printQuestion();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showCategories() {
        try {
            String jsonResponse = makeHttpRequest("https://opentdb.com/api_category.php");

            CategoryList categoryList = mapper.readValue(jsonResponse, CategoryList.class);

            for (Category category : categoryList.getCategories()) {
                System.out.println(category.getId() + ": " + category.getName());
            }
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

    private void setDifficulty (){
        System.out.println("Chose difficulty");
        System.out.println("1> Easy\n2> Medium\n3> Hard");
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                int input = scanner.nextInt();
                if (input >= 1 && input <= 3) {

                    if (input == 1) this.difficulty = "easy";
                    if (input == 2) this.difficulty = "medium";
                    if (input == 3) this.difficulty = "hard";
                    break;
                } else {
                    System.out.println("Try again!");
                }
            } catch (Exception e) {
                System.out.println("Try again!");
            }
        }

    }
    private String linkFormatter(int category, int numberOfQuestions, String difficulty) {

        String link = "https://opentdb.com/api.php?amount=" + numberOfQuestions + "&category=" + category + "&difficulty=" + difficulty;
        return link;
    }
}
