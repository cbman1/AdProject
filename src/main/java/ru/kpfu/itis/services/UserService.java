package ru.kpfu.itis.services;


import ru.kpfu.itis.dao.SignUpDao;
import ru.kpfu.itis.models.FileInfo;
import ru.kpfu.itis.models.User;

import java.util.Optional;

public interface UserService {
    void signUp(SignUpDao signUpData);
    boolean isRegisterUser(SignUpDao signUpDto);
    void updateInformationUser(User user, String city, String address);
    User getUserById(Long id);
    Optional<User> getUserByEmail(String email);
    void setAvatarUuid(FileInfo fileInfo, User user);
    String getAvatarUUIDById(User user);
}
