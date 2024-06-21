package com.dictionaryapp.model.dtos;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class AddWordDTO {
    @NotBlank(message = "Term must not be empty")
    @Size(min = 2, max = 40, message = "The term length must be between 2 and 40 characters!")
    private String term;
    @NotBlank(message = "Translation must not be empty")
    @Size(min = 2, max = 80,  message = "The translation length must be between 2 and 80 characters!")
    private String translation;
    @Size(min = 2, max = 200,  message = "The example length must be between 2 and 40 characters!")
    private String example;

    @PastOrPresent(message = "The input date must be in the past or present!")
    private LocalDate inputDate;
    @NotBlank(message = "You must select a language")
    private String language;

    public AddWordDTO() {
    }

    public String getTerm() {
        return term;
    }

    public AddWordDTO setTerm(String term) {
        this.term = term;
        return this;
    }

    public String getTranslation() {
        return translation;
    }

    public AddWordDTO setTranslation(String translation) {
        this.translation = translation;
        return this;
    }

    public String getExample() {
        return example;
    }

    public AddWordDTO setExample(String example) {
        this.example = example;
        return this;
    }

    public LocalDate getInputDate() {
        return inputDate;
    }

    public AddWordDTO setInputDate(LocalDate inputDate) {
        this.inputDate = inputDate;
        return this;
    }

    public String getLanguage() {
        return language;
    }

    public AddWordDTO setLanguage(String language) {
        this.language = language;
        return this;
    }
}
