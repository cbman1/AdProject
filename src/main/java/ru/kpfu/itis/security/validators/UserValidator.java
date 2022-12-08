package ru.kpfu.itis.security.validators;

import ru.kpfu.itis.models.User;

public class UserValidator {
    public void validateUser(User user) {
        if (user.getFirstName() == null || user.getFirstName().length() <= 3 || user.getFirstName().length() >= 20) {
            throw new IllegalArgumentException("First name length must be less than 20 and greater than 3.");
        }
        if (user.getLastName() == null || user.getLastName().length() <= 3 || user.getLastName().length() >= 20) {
            throw new IllegalArgumentException("Last name length must be less than 20 and greater than 3.");
        }
        if (user.getPassword() == null || user.getPassword().length() <= 6 || user.getPassword().length() >= 40) {
            throw new IllegalArgumentException("Password length must be less than 40 and greater than 6.");
        }
        if (user.getEmail() == null || user.getEmail().length() <= 7 || user.getEmail().length() >= 60) {
            throw new IllegalArgumentException("Email length must be less than 60 and greater than 7.");
        }
        if (user.getPhoneNumber() == null || user.getPhoneNumber().toString().length() != 11) {
            throw new IllegalArgumentException("Phone number length must be 11.");
        }
    }
}
