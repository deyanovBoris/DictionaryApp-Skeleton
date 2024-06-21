package com.dictionaryapp.controller;

import com.dictionaryapp.config.CurrentUserSession;
import com.dictionaryapp.model.dtos.UserRegisterDTO;
import com.dictionaryapp.service.UserService;
import com.dictionaryapp.model.dtos.LoginUserDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {
    private final UserService userService;
    private final CurrentUserSession currentUserSession;

    public UserController(UserService userService, CurrentUserSession currentUserSession) {
        this.userService = userService;
        this.currentUserSession = currentUserSession;
    }

    //Registration//
    @ModelAttribute("registerData")
    public UserRegisterDTO registerDTO(){
        return new UserRegisterDTO();
    }
    @GetMapping("/register")
    public String register(Model model){
        if (this.currentUserSession.isLoggedIn()){
            return "redirect:/";
        }

        return "register";
    }
    @PostMapping("/register")
    public String doRegister(@Valid UserRegisterDTO data,
                             BindingResult bindingResult,
                             RedirectAttributes rAtt){
        if (bindingResult.hasErrors() || !this.userService.registerUser(data)){
            rAtt.addFlashAttribute("registerData", data);
            rAtt.addFlashAttribute(
                    "org.springframework.validation.BindingResult.registerData",
                    bindingResult);

            return "redirect:/register";
        }

        return "redirect:/login";
    }

    //Login//
    @ModelAttribute("loginData")
    public LoginUserDTO loginUserDTO(){
        return new LoginUserDTO();
    }

    @GetMapping("/login")
    public String login(Model model){
        if (this.currentUserSession.isLoggedIn()){
            return "redirect:/";
        }

        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@Valid LoginUserDTO data,
                          BindingResult bindingResult,
                          RedirectAttributes rAtt){
        if (bindingResult.hasErrors()){
            rAtt.addFlashAttribute("loginData", data);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.loginData",
                    bindingResult);

            return "redirect:/login";
        }

        boolean success = userService.loginUser(data);

        if (!success){
            rAtt.addFlashAttribute("loginData", data);
            rAtt.addFlashAttribute("userPassMismatch", true);

            return "redirect:/login";
        }

        return "redirect:/home";
    }

    @PostMapping("/logout")
    public String doLogout(){
        if (this.currentUserSession.isLoggedIn()){
            this.currentUserSession.logout();
        }
        return "redirect:/";
    }



}
