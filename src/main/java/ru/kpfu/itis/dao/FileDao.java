package ru.kpfu.itis.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kpfu.itis.models.FileInfo;

import java.io.InputStream;
import java.nio.file.Path;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileDao {
    private Long size;
    private String fileName;
    private String mimeType;
    private String type;
    private String originalFileName;
    private Long loadAccount;

    private InputStream fileStream;
    private Path path;
}
