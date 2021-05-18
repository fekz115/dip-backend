package org.fekz115.dip.configuration;

import org.fekz115.dip.repository.MusicRepository;
import org.fekz115.dip.repository.PictureRepository;
import org.fekz115.dip.repository.UserRepository;
import org.fekz115.dip.repository.VideoRepository;
import org.fekz115.dip.service.FileService;
import org.fekz115.dip.service.MediaService;
import org.fekz115.dip.service.UserNotificationService;
import org.fekz115.dip.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
public class ServiceConfiguration {

    @Bean
    UserService userService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            UserNotificationService mailService
    ) {
        return new UserService(
                userRepository,
                passwordEncoder::encode,
                passwordEncoder::matches,
                () -> String.valueOf((int)(Math.random() * 5)),
                mailService
        );
    }

    @Bean
    MediaService mediaService(
            FileService fileService,
            PictureRepository pictureRepository,
            VideoRepository videoRepository,
            MusicRepository musicRepository
    ) {
        return new MediaService(pictureRepository, videoRepository, musicRepository, fileService);
    }

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver commonsMultipartResolver(){
        return new CommonsMultipartResolver();
    }

}
