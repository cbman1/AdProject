package ru.kpfu.itis.services;

import ru.kpfu.itis.dao.FileDao;
import ru.kpfu.itis.models.FileInfo;
import ru.kpfu.itis.models.User;

import java.io.File;

public interface FilesService {
    void upload(FileDao file);
    void addAvatarUser(FileInfo fileInfo, User user);
    FileInfo getFileInfoAvatarById(User user);
    void deleteOldAvatar(User user);
}
