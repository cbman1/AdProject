package ru.kpfu.itis.repositories;

import ru.kpfu.itis.models.FileInfo;
import ru.kpfu.itis.models.User;

public interface FilesRepository {
    void save(FileInfo fileInfo);
    void addAvatarUser(FileInfo fileInfo, User user);
    FileInfo getFileInfoAvatarByUserId(User user);
    void deleteAvatarUser(User user);
}
