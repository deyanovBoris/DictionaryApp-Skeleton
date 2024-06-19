package com.dictionaryapp.init;

import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.model.entity.LanguageName;
import com.dictionaryapp.repo.LanguageRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InitLanguages implements CommandLineRunner {

    private final LanguageRepository languageRepository;

    public InitLanguages(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (this.languageRepository.count() == 0){
            Language german = new Language()
                    .setName(LanguageName.GERMAN)
                    .setDescription("A West Germanic language, is spoken by over 90 million " +
                            "people worldwide. Known for its complex grammar and compound words," +
                            " it's the official language of Germany and widely used in Europe.");
            Language spanish = new Language()
                    .setName(LanguageName.SPANISH)
                    .setDescription("A Romance language, is spoken by over 460 million people " +
                            "worldwide. It boasts a rich history, diverse dialects, and is known" +
                            " for its melodious sound, making it a global cultural treasure.");
            Language french = new Language()
                    .setName(LanguageName.FRENCH)
                    .setDescription("A Romance language spoken worldwide, known for its elegance " +
                            "and cultural richness. It's the official language of France and numerous " +
                            "nations, famed for its cuisine, art, and literature.");
            Language italian = new Language()
                    .setName(LanguageName.ITALIAN)
                    .setDescription("A Romance language spoken in Italy and parts of Switzerland," +
                            " with rich cultural heritage. Known for its melodious sounds," +
                            " it's a gateway to Italian art, cuisine, and history.");

            this.languageRepository.saveAllAndFlush(List.of(german, spanish, french, italian));
        }
    }
}
