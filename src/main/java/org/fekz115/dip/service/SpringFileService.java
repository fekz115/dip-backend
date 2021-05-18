package org.fekz115.dip.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.UUID;

@Service
public class SpringFileService implements FileService {

    @Value("${media.path}")
    String path;

    @Override
    public String saveFile(byte[] file, String fileName, MediaType type) throws IOException {
        String finalPath = path + type.name().toLowerCase() + "/" + UUID.randomUUID() + "-" + fileName;
        File f = new File(finalPath);
        try (OutputStream outStream = new FileOutputStream(f)) {
            outStream.write(file);
        }
        return finalPath;
    }

    @Override
    public File loadFile(String fileName) {
        return new File(fileName);
    }
}
