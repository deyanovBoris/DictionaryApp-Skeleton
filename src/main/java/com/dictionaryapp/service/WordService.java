package com.dictionaryapp.service;

import com.dictionaryapp.model.dtos.AddWordDTO;
import com.dictionaryapp.model.dtos.WordInfoDTO;
import com.dictionaryapp.model.entity.LanguageName;

import java.util.List;

public interface WordService {
    boolean addWord(AddWordDTO addWordDTO);

    List<WordInfoDTO> findWordsByLanguage(LanguageName languageName);

    long getTotalWords();

    void deleteWord(long id);

    void deleteAllWords();
}
