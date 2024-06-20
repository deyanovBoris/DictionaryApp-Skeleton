package com.dictionaryapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WordAddController {
    @GetMapping("/word-add")
    public String wordAdd(){
        return "word-add";
    }
}
