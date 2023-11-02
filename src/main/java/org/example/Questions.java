package org.example;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Questions {

    private String category;
    private String type;
    private String difficulty;
    private String question;
    private String correct_answer;
    private List<String> incorrect_answers;

    // Getters and setters (you can generate these using your IDE)

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }

    public List<String> getIncorrect_answers() {
        return incorrect_answers;
    }

    public void setIncorrect_answers(List<String> incorrect_answers) {
        this.incorrect_answers = incorrect_answers;
    }

    @Override
    public String toString() {
        return "Category: " + category + "\n" +
                "Type: " + type + "\n" +
                "Difficulty: " + difficulty + "\n" +
                "Question: " + question + "\n" +
                "Correct Answer: " + correct_answer + "\n" +
                "Incorrect Answers: " + incorrect_answers;
    }
    public boolean printQuestion (){

        System.out.println(getQuestion() + "?");
        List<String> alternativ = getIncorrect_answers();
        alternativ.add(getCorrect_answer());
        int number = 0;
        int correctAnswer = 0;
        Collections.shuffle(alternativ);
        for (String answer : alternativ) {
            System.out.println( number + "> " + answer);
            if (answer.equalsIgnoreCase(getCorrect_answer())) {
                number = correctAnswer;
            }
            number++;
        }
        Scanner scan = new Scanner(System.in);
        int input = scan.nextInt();

        if (input == correctAnswer) {
            System.out.println("Correct!");
            return true;
        } else {
            System.out.println("Worng! " + "correct answer is: " + getCorrect_answer());
            return false;
        }

    }
}
