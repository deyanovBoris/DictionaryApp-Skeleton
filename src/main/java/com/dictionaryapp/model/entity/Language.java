package com.dictionaryapp.model.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "languages")
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private LanguageName name;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @OneToMany(mappedBy = "language")
    private Set<Word> words;

    public Language() {
        this.words = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public Language setId(long id) {
        this.id = id;
        return this;
    }

    public LanguageName getName() {
        return name;
    }

    public Language setName(LanguageName name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Language setDescription(String description) {
        this.description = description;
        return this;
    }

    public Set<Word> getWords() {
        return words;
    }

    public Language setWords(Set<Word> words) {
        this.words = words;
        return this;
    }
}
