package com.pfe.bookstore.utils;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.Files.copy;
import static java.nio.file.Paths.get;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class FileHandler {
    private static String BOOKDIRECTORY = "src/main/resources/static/books/";

    public  static void  uploadFile(MultipartFile file) throws IOException {
            String filename = StringUtils.cleanPath(file.getOriginalFilename());
            Path fileStorage = get(BOOKDIRECTORY, filename).toAbsolutePath().normalize();
            copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);
    }
public static Resource downloadFile(String filename)  throws IOException {
    Path filePath = get(BOOKDIRECTORY).toAbsolutePath().normalize().resolve(filename);
    if(!Files.exists(filePath)) {
        throw new FileNotFoundException(filename + " was not found on the server");
    }
    Resource resource = new UrlResource(filePath.toUri());
    return resource;
}
}
