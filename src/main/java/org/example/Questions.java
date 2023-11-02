package org.example;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

@Data
public class Questions {

    private String category;
    private String type;
    private String difficulty;
    private String question;
    private String correct_answer;
    private List<String> incorrect_answers;


    public void printQuestion (){

        System.out.println(getQuestion() + "?");
        List<String> alternatives = new ArrayList<>(getIncorrect_answers());
        alternatives.add(getCorrect_answer());
        Collections.shuffle(alternatives);

        int correctAnswer = alternatives.indexOf(getCorrect_answer()) + 1;

        for (int i = 1; i <= alternatives.size(); i++) {
            System.out.println(i + "> " + alternatives.get(i - 1));
        }

        checkAnswer(correctAnswer);

    }
    private void checkAnswer (int correctAnswer){

        Scanner scan = new Scanner(System.in);
        int input = scan.nextInt();

        if (input == correctAnswer) {
            System.out.println("Correct!");
        } else {
            System.out.println("Wrong! The correct answer is: " + getCorrect_answer());
        }
    }
}
