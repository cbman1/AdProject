package ru.kpfu.itis.services.impl;


import ru.kpfu.itis.dao.SignUpDao;
import ru.kpfu.itis.models.FileInfo;
import ru.kpfu.itis.models.User;
import ru.kpfu.itis.repositories.FilesRepository;
import ru.kpfu.itis.repositories.UserRepository;
import ru.kpfu.itis.security.validators.UserValidator;
import ru.kpfu.itis.services.FilesService;
import ru.kpfu.itis.services.UserService;
import ru.kpfu.itis.utils.UserPasswordHasher;

import java.util.Optional;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserValidator userValidator;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.userValidator = new UserValidator();
    }

    @Override
    public void signUp(SignUpDao signUpData) {
        User user = User.builder()
                .firstName(signUpData.getFirstName())
                .lastName(signUpData.getLastName())
                .password(signUpData.getPassword())
                .email(signUpData.getEmail())
                .phoneNumber(signUpData.getPhoneNumber())
                .build();
        userValidator.validateUser(user);
        user.setPassword(UserPasswordHasher.getHashedPassword(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public boolean isRegisterUser(SignUpDao signUpDto) {
        return userRepository.findOneByEmail(signUpDto.getEmail()).isPresent();
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findOneByEmail(email);
    }

    @Override
    public void updateInformationUser(User user, String city, String address) {
        user.setCity(city);
        user.setAddress(address);
        userRepository.updateInformationUser(user, city, address);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.getUserById(id);
    }

    @Override
    public void setAvatarUuid(FileInfo fileInfo, User user) {
        user.setAvatar_uuid(fileInfo.getStorageFileName());
        userRepository.newAvatarUuid(fileInfo, user);
    }

    @Override
    public String getAvatarUUIDById(User user) {
        return userRepository.getAvatarUuidById(user);
    }
}
