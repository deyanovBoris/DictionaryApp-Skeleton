package com.dictionaryapp.controller;

import com.dictionaryapp.model.entity.LanguageName;
import com.dictionaryapp.service.LanguageService;
import com.dictionaryapp.service.UserService;
import com.dictionaryapp.service.WordService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final UserService userService;
    private final WordService wordService;


    public HomeController(UserService userService, WordService wordService) {
        this.userService = userService;
        this.wordService = wordService;
    }

    @GetMapping("/")
    public String index(Model model){
        if (this.userService.isCurrentUserLoggedIn()){
            return "redirect:/home";
        }

        return "index";
    }

    @GetMapping("/home")
    public String home(Model model){
        if (!this.userService.isCurrentUserLoggedIn()){
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
