package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Category {
    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

