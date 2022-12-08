package ru.kpfu.itis.security;

public interface AuthenticationManager {
    boolean authenticate(String email, String password);
}
