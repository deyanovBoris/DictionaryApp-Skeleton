package com.dictionaryapp.service;

import com.dictionaryapp.model.dtos.UserRegisterDTO;
import com.dictionaryapp.model.dtos.LoginUserDTO;

public interface UserService {
    boolean registerUser(UserRegisterDTO userRegisterDTO);

    boolean loginUser(LoginUserDTO data);
}
