package com.dictionaryapp.service.impl;

import com.dictionaryapp.model.dtos.AddWordDTO;
import com.dictionaryapp.model.dtos.WordInfoDTO;
import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.model.entity.LanguageName;
import com.dictionaryapp.model.entity.User;
import com.dictionaryapp.model.entity.Word;
import com.dictionaryapp.repo.LanguageRepository;
import com.dictionaryapp.repo.UserRepository;
import com.dictionaryapp.repo.WordRepository;
import com.dictionaryapp.service.UserService;
import com.dictionaryapp.service.WordService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WordServiceImpl implements WordService {
    private final UserService userService;
    private final WordRepository wordRepository;
    private final UserRepository userRepository;
    private final LanguageRepository languageRepository;
    private final ModelMapper modelMapper;

    public WordServiceImpl(UserService userService, WordRepository wordRepository, UserRepository userRepository, LanguageRepository languageRepository, ModelMapper modelMapper) {
        this.userService = userService;
        this.wordRepository = wordRepository;
        this.userRepository = userRepository;
        this.languageRepository = languageRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean addWord(AddWordDTO addWordDTO) {
        Word wordAdd = this.modelMapper.map(addWordDTO, Word.class);

        Optional<Language> languageByLanguageName = this.languageRepository
                .findByLanguageName(LanguageName
                        .valueOf(addWordDTO.getLanguage()));

        if (!languageByLanguageName.isPresent()){
            return false;
        }
        wordAdd.setLanguage(languageByLanguageName.get());

        Optional<User> userById = this.userRepository.findById(this.userService.getCurrentUserId());

        if (!userById.isPresent()) {
            return false;
        }
        wordAdd.setAddedBy(userById.get());

        this.wordRepository.saveAndFlush(wordAdd);
        return true;
    }

    public List<WordInfoDTO> findWordsByLanguage(LanguageName languageName) {
        List<WordInfoDTO> collect = this.wordRepository.findAllByLanguageLanguageName(languageName)
                .stream()
                .map(word -> this.modelMapper.map(word, WordInfoDTO.class))
                .collect(Collectors.toList());

        return collect;
    }

    @Override
    public long getTotalWords() {
        return this.wordRepository.count();
    }

    @Override
    public void deleteWord(long id) {
        wordRepository.deleteById(id);
    }

    @Override
    public void deleteAllWords() {
        wordRepository.deleteAll();
    }


}
