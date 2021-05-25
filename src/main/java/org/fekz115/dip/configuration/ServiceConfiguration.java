package org.fekz115.dip.configuration;

import org.fekz115.dip.repository.*;
import org.fekz115.dip.service.*;
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

    @Bean
    ContentBodyService contentBodyService(
            ContentBodyRepository contentBodyRepository,
            TextRepository textRepository,
            PictureRepository pictureRepository,
            VideoRepository videoRepository,
            MusicRepository musicRepository,
            ContentContainerRepository contentContainerRepository
    ) {
        return new ContentBodyService(contentBodyRepository, textRepository, musicRepository, videoRepository, pictureRepository, contentContainerRepository);
    }

    @Bean
    ArticleService articleService(
            ArticleRepository articleRepository,
            ContentBodyService contentBodyService,
            TagRepository tagRepository
    ) {
        return new ArticleService(articleRepository, tagRepository, contentBodyService);
    }

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver commonsMultipartResolver(){
        return new CommonsMultipartResolver();
    }

}
