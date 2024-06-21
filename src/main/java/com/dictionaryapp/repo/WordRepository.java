package com.dictionaryapp.repo;

import com.dictionaryapp.model.entity.LanguageName;
import com.dictionaryapp.model.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {
    List<Word> findAllByLanguageLanguageName(LanguageName languageName);
}
