package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CategoryList {
    @JsonProperty("trivia_categories")
    private List<Category> categories;

    public List<Category> getCategories() {
        return categories;
    }
}
