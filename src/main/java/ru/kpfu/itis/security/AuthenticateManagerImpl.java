package ru.kpfu.itis.security;

import ru.kpfu.itis.models.User;
import ru.kpfu.itis.repositories.UserRepository;

public class AuthenticateManagerImpl implements AuthenticationManager {
    private final UserRepository userRepository;

    public AuthenticateManagerImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean authenticate(String email, String password) {
        User user = userRepository.findOneByEmail(email).orElse(null);
        if (user == null) {
            return false;
        }
        return user.getPassword().equals(password);
    }
}
