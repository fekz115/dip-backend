package org.fekz115.dip.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface FileService {
    String saveFile(byte[] file, String fileName, MediaType type) throws FileNotFoundException, IOException;
    File loadFile(String fileName);
}
