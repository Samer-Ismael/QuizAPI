package org.example;

import java.util.List;

public class TriviaResponse {
    private int response_code;
    private List<Questions> results;

    public int getResponse_code() {
        return response_code;
    }

    public void setResponse_code(int response_code) {
        this.response_code = response_code;
    }

    public List<Questions> getResults() {
        return results;
    }

    public void setResults(List<Questions> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Questions question : results) {
            sb.append("Category: ").append(question.getCategory()).append("\n");
            sb.append("Type: ").append(question.getType()).append("\n");
            sb.append("Difficulty: ").append(question.getDifficulty()).append("\n");
            sb.append("Question: ").append(question.getQuestion()).append("\n");
            sb.append("Correct Answer: ").append(question.getCorrect_answer()).append("\n");
            sb.append("Incorrect Answers: ").append(question.getIncorrect_answers()).append("\n");
            sb.append("\n");
        }
        return sb.toString();
    }
}
