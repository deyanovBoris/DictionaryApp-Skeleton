package com.dictionaryapp.service.impl;

import com.dictionaryapp.model.dtos.UserRegisterDTO;
import com.dictionaryapp.model.entity.User;
import com.dictionaryapp.repo.UserRepository;
import com.dictionaryapp.config.CurrentUserSession;
import com.dictionaryapp.service.UserService;
import com.dictionaryapp.model.dtos.LoginUserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final CurrentUserSession currentUser;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public UserServiceImpl(CurrentUserSession currentUser, UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.currentUser = currentUser;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean registerUser(UserRegisterDTO userRegisterDTO) {
         if (!userRegisterDTO.getPassword()
                .equals(userRegisterDTO.getConfirmPassword())){
            return false;
        }

        boolean isUsernameOrEmailTaken = this.userRepository.existsByUsernameOrEmail(
                userRegisterDTO.getUsername(),
                userRegisterDTO.getEmail());

        if (isUsernameOrEmailTaken){
            return false;
        }

        User user = this.modelMapper.map(userRegisterDTO, User.class)
                .setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));

        this.userRepository.saveAndFlush(user);
        return true;
    }

    @Override
    public boolean loginUser(LoginUserDTO data) {
        Optional<User> userByUsername = this.userRepository.findByUsername(data.getUsername());

        if (userByUsername.isEmpty()){
            return false;
        }

        User user = userByUsername.get();

        if (!this.passwordEncoder.matches(data.getPassword(), user.getPassword())){
            return false;
        }

        this.currentUser.login(user);

        return true;
    }

    @Override
    public boolean logoutUser() {
        if (currentUser.isLoggedIn()){
            currentUser.clear();
            return true;
        }

        return false;
    }

    @Override
    public boolean isCurrentUserLoggedIn() {
        return this.currentUser.isLoggedIn();
    }

    @Override
    public long getCurrentUserId() {
        return this.currentUser.getId();
    }
}
