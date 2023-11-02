package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class CategoryList {
    @JsonProperty("trivia_categories")
    private List<Category> categories;
}
