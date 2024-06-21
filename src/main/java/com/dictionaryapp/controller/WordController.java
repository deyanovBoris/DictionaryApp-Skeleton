package com.dictionaryapp.controller;

import com.dictionaryapp.model.dtos.AddWordDTO;
import com.dictionaryapp.service.UserService;
import com.dictionaryapp.service.WordService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class WordController {
    private final UserService userService;
    private final WordService wordService;
    public WordController(UserService userService, WordService wordService) {
        this.userService = userService;
        this.wordService = wordService;
    }

    @ModelAttribute("wordData")
    public AddWordDTO addWordDTO(){
        return new AddWordDTO();
    }

    @GetMapping("/word_add")
    public String wordAdd(){
        if (this.userService.isCurrentUserLoggedIn()){
            return "word-add";
        }

        return "redirect:/home";
    }

    @PostMapping("/word_add")
    public String doWordAdd(@Valid AddWordDTO data,
                            BindingResult bindingResult,
                            RedirectAttributes rAtt){
        if (bindingResult.hasErrors()){
            rAtt.addFlashAttribute("wordData", data);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.wordData",
                    bindingResult);

            return "redirect:/word_add";
        }

        boolean success = this.wordService.addWord(data);

        if (!success){
            rAtt.addFlashAttribute("wordData", data);
            rAtt.addFlashAttribute("nonExistingLanguage", true);

            return "redirect:/word_add";
        }

        return "redirect:/";
    }

    @DeleteMapping("/home/remove/{id}")
    public String deleteWordById(@PathVariable("id") long id){
        if (this.userService.isCurrentUserLoggedIn()){
            this.wordService.deleteWord(id);
        }

        return "redirect:/home";
    }

    @DeleteMapping("/home/remove-all")
    public String deleteAllWords(){
        if (this.userService.isCurrentUserLoggedIn()){
            this.wordService.deleteAllWords();
        }

        return "redirect:/home";
    }
}
