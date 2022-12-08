package ru.kpfu.itis.repositories;

import ru.kpfu.itis.models.FileInfo;
import ru.kpfu.itis.models.User;

import java.util.Optional;

public interface UserRepository {
    void save(User user);
    User getUserById(Long id);

    void updateInformationUser(User user, String city, String address);
    Optional<User> findOneByEmail(String email);
    void newAvatarUuid(FileInfo fileInfo, User user);
    String getAvatarUuidById(User user);
}
