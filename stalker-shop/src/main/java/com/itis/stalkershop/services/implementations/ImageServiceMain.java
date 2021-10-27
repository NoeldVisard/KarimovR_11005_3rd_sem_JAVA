package com.itis.stalkershop.services.implementations;

import com.itis.stalkershop.models.Image;
import com.itis.stalkershop.models.ImageDto;
import com.itis.stalkershop.repositories.interfaces.FilesRepository;
import com.itis.stalkershop.services.interfaces.ImageService;
import com.itis.stalkershop.utils.exceptions.NotFoundException;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

public class ImageServiceMain implements ImageService {
    private static final String PATH_DEFAULT =
            "D:\\Projects\\Java\\KarimovR_11005_3rd_sem_JAVA\\stalker-shop\\data\\images\\";

    private final FilesRepository filesRepository;

    public ImageServiceMain(
            FilesRepository filesRepository
    ) {
        this.filesRepository = filesRepository;
    }

    @NotNull
    @Override
    public Image saveFileToStorage(
            @NotNull InputStream inputStream,
            @NotNull String originalFileName,
            @NotNull String contentType,
            long size
    ) {
        ImageDto fileInfo = new ImageDto(
                UUID.randomUUID().toString(),
                originalFileName,
                contentType,
                size
        );

        Image f;
        try {
            Files.copy(
                    inputStream,
                    Paths.get(PATH_DEFAULT +
                            fileInfo.getStorageName() + "." +
                            fileInfo.getType().split("/")[1]
                    )
            );
            f = filesRepository.save(fileInfo);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        return f;
    }

    // TODO: inspect warning
    @Override
    public void writeFileFromStorage(
            long fileId,
            @NotNull OutputStream outputStream
    ) {
        Optional<Image> optionalFileInfo = filesRepository.findByPrimaryKey(fileId);
        Image fileInfo = optionalFileInfo.orElseThrow(
                () -> new NotFoundException("File not found")
        );

        File file = new File(PATH_DEFAULT +
                fileInfo.getStorageName() + "." +
                fileInfo.getType().split("/")[1]
        );

        try {
            Files.copy(file.toPath(), outputStream);
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }

    // TODO: inspect warning
    @NotNull
    @Override
    public Image getFileInfo(long fileId) {
        return filesRepository.findByPrimaryKey(fileId).orElseThrow(
                () -> new NotFoundException("File not found")
        );
    }


}