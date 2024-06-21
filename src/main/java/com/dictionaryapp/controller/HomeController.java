package com.dictionaryapp.controller;

import com.dictionaryapp.config.CurrentUserSession;
import com.dictionaryapp.model.entity.LanguageName;
import com.dictionaryapp.service.WordService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final CurrentUserSession currentUserSession;
    private final WordService wordService;


    public HomeController(CurrentUserSession currentUserSession, WordService wordService) {
        this.currentUserSession = currentUserSession;
        this.wordService = wordService;
    }

    @GetMapping("/")
    public String notLogged(Model model){
        if (this.currentUserSession.isLoggedIn()){
            return "redirect:/home";
        }

        return "index";
    }

    @GetMapping("/home")
    public String logged(Model model){
        if (!this.currentUserSession.isLoggedIn()){
            return "redirect:/";
        }

        model.addAttribute("allWordsCount", this.wordService.getTotalWords());
        model.addAttribute("germanWords", this.wordService.findWordsByLanguage(LanguageName.GERMAN));
        model.addAttribute("frenchWords", this.wordService.findWordsByLanguage(LanguageName.FRENCH));
        model.addAttribute("spanishWords", this.wordService.findWordsByLanguage(LanguageName.SPANISH));
        model.addAttribute("italianWords", this.wordService.findWordsByLanguage(LanguageName.ITALIAN));


        return "home";
    }

}
