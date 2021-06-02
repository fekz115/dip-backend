package org.fekz115.dip.controller;

import lombok.AllArgsConstructor;
import org.fekz115.dip.model.Music;
import org.fekz115.dip.model.Picture;
import org.fekz115.dip.model.Video;
import org.fekz115.dip.service.MediaService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@AllArgsConstructor
@RestController
@RequestMapping("/media")
public class MediaController {

    private final MediaService mediaService;
    private final ResourceLoader resourceLoader;

    @PostMapping("/picture/create")
    public int createPicture(@RequestBody String name) {
        return mediaService.createPicture(name);
    }

    @PostMapping("/video/create")
    public int createVideo(@RequestBody String name) {
        return mediaService.createVideo(name);
    }

    @PostMapping("/music/create")
    public int createMusic(@RequestBody String name) {
        return mediaService.createMusic(name);
    }

    @PostMapping(value = "/picture/upload/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Picture uploadPicture(@RequestPart("file") MultipartFile multipartFile, @PathVariable int id) throws IOException {
        return mediaService.savePicture(multipartFile.getBytes(), multipartFile.getOriginalFilename(), id);
    }

    @PostMapping(value = "/video/video/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Video uploadVideo(@RequestPart("file") MultipartFile multipartFile, @PathVariable int id) throws IOException {
        return mediaService.saveVideo(multipartFile.getBytes(), multipartFile.getOriginalFilename(), id);
    }

    @PostMapping(value = "/music/upload/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Music uploadMusic(@RequestPart("file") MultipartFile multipartFile, @PathVariable int id) throws IOException {
        return mediaService.saveMusic(multipartFile.getBytes(), multipartFile.getOriginalFilename(), id);
    }

    @GetMapping("/picture/{id}")
    public ResponseEntity<ByteArrayResource> loadPicture(@PathVariable int id) throws IOException {
        return serveFile(mediaService.loadPicture(id));
    }

    @GetMapping("/video/{id}")
    public ResponseEntity<ByteArrayResource> loadVideo(@PathVariable int id) throws IOException {
        return serveFile(mediaService.loadVideo(id));
    }

    @GetMapping("/music/{id}")
    public ResponseEntity<ByteArrayResource> loadMusic(@PathVariable int id) throws IOException {
        return serveFile(mediaService.loadMusic(id));
    }

    private ResponseEntity<ByteArrayResource> serveFile(File file) throws IOException {
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + URLEncoder.encode(file.getName(), StandardCharsets.UTF_8.toString()) + "\"").body(new ByteArrayResource(new FileInputStream(file).readAllBytes()));
    }
}
