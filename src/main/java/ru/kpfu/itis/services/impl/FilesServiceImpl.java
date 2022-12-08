package ru.kpfu.itis.services.impl;

import ru.kpfu.itis.dao.FileDao;
import ru.kpfu.itis.models.FileInfo;
import ru.kpfu.itis.models.User;
import ru.kpfu.itis.repositories.FilesRepository;
import ru.kpfu.itis.services.FilesService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

public class FilesServiceImpl implements FilesService {
    private String storagePath = "C:\\Users\\79174\\IdeaProjects\\AdProject\\storage\\";
    private final FilesRepository filesRepository;

    public FilesServiceImpl(FilesRepository filesRepository) {
        this.filesRepository = filesRepository;
    }

    @Override
    public void upload(FileDao file) {
        String originalFileName = file.getFileName();
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String storageFileName = UUID.randomUUID() + extension;

        FileInfo fileInfo = FileInfo.builder()
                .size(file.getSize())
                .mimeType(file.getMimeType())
                .originalFileName(originalFileName)
                .type(file.getType())
                .storageFileName(storageFileName)
                .loadAccount(file.getLoadAccount())
                .build();
        try {
            Files.copy(file.getFileStream(), Paths.get(storagePath + storageFileName));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        filesRepository.save(fileInfo);
    }

    @Override
    public FileInfo getFileInfoAvatarById(User user) {
        return filesRepository.getFileInfoAvatarByUserId(user);
    }

    @Override
    public void addAvatarUser(FileInfo fileInfo, User user) {
        filesRepository.addAvatarUser(fileInfo, user);
    }

    @Override
    public void deleteOldAvatar(User user) {
        filesRepository.deleteAvatarUser(user);
    }
}
