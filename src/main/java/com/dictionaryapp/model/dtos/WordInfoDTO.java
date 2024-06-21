package com.dictionaryapp.model.dtos;

import java.time.LocalDate;

public class WordInfoDTO {
    private long id;
    private String term;
    private String translation;
    private String example;
    private String addedByUsername;
    private LocalDate inputDate;

    public WordInfoDTO() {
    }

    public long getId() {
        return id;
    }

    public WordInfoDTO setId(long id) {
        this.id = id;
        return this;
    }

    public String getTerm() {
        return term;
    }

    public WordInfoDTO setTerm(String term) {
        this.term = term;
        return this;
    }

    public String getTranslation() {
        return translation;
    }

    public WordInfoDTO setTranslation(String translation) {
        this.translation = translation;
        return this;
    }

    public String getExample() {
        return example;
    }

    public WordInfoDTO setExample(String example) {
        this.example = example;
        return this;
    }

    public String getAddedByUsername() {
        return addedByUsername;
    }

    public WordInfoDTO setAddedByUsername(String addedByUsername) {
        this.addedByUsername = addedByUsername;
        return this;
    }

    public LocalDate getInputDate() {
        return inputDate;
    }

    public WordInfoDTO setInputDate(LocalDate inputDate) {
        this.inputDate = inputDate;
        return this;
    }
}
