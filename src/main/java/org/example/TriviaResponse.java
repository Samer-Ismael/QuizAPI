package org.example;

import lombok.Data;

import java.util.List;

@Data
public class TriviaResponse {
    private int response_code;
    private List<Questions> results;

}
