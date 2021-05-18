package org.fekz115.dip.service;

import lombok.AllArgsConstructor;
import org.fekz115.dip.model.Music;
import org.fekz115.dip.model.Picture;
import org.fekz115.dip.model.Video;
import org.fekz115.dip.repository.MusicRepository;
import org.fekz115.dip.repository.PictureRepository;
import org.fekz115.dip.repository.VideoRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@AllArgsConstructor
public class MediaService {
    private final PictureRepository pictureRepository;
    private final VideoRepository videoRepository;
    private final MusicRepository musicRepository;
    private final FileService fileService;

    public Picture savePicture(byte[] file, String fileName, int id) throws IOException {
        Picture picture = pictureRepository.findById(id).orElseThrow(FileNotFoundException::new);
        picture.setFileName(fileService.saveFile(file, fileName, MediaType.IMAGE));
        return pictureRepository.save(picture);
    }

    public Video saveVideo(byte[] file, String fileName, int id) throws IOException {
        Video video = videoRepository.findById(id).orElseThrow(FileNotFoundException::new);
        video.setFileName(fileService.saveFile(file, fileName, MediaType.VIDEO));
        return videoRepository.save(video);
    }
    
    public Music saveMusic(byte[] file, String fileName, int id) throws IOException {
        Music music = musicRepository.findById(id).orElseThrow(FileNotFoundException::new);
        music.setFileName(fileService.saveFile(file, fileName, MediaType.MUSIC));
        return musicRepository.save(music);
    }

    public int createPicture(String name) {
        var picture = new Picture();
        picture.setName(name);
        return pictureRepository.save(picture).getId();
    }

    public int createVideo(String name) {
        var video = new Video();
        video.setName(name);
        return videoRepository.save(video).getId();
    }

    public int createMusic(String name) {
        var music = new Music();
        music.setName(name);
        return musicRepository.save(music).getId();
    }

    public File loadPicture(int id) throws FileNotFoundException {
        return fileService.loadFile(pictureRepository.findById(id).orElseThrow(FileNotFoundException::new).getFileName());
    }

    public File loadVideo(int id) throws FileNotFoundException {
        return fileService.loadFile(videoRepository.findById(id).orElseThrow(FileNotFoundException::new).getFileName());
    }

    public File loadMusic(int id) throws FileNotFoundException {
        return fileService.loadFile(musicRepository.findById(id).orElseThrow(FileNotFoundException::new).getFileName());
    }
}
