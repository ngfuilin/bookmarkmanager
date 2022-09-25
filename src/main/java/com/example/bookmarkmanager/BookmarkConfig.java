package com.example.bookmarkmanager;

import com.example.bookmarkmanager.model.Bookmark;
import com.example.bookmarkmanager.model.Folder;
import com.example.bookmarkmanager.repositories.BookmarkRepository;
import com.example.bookmarkmanager.repositories.FolderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Configuration
public class BookmarkConfig {

    @Bean
    CommandLineRunner commandLineRunner(
            BookmarkRepository repository, FolderRepository folderRepository) {
        return args -> {

           Bookmark bm1 = new Bookmark("tourism",
                    "https://www.teckguan.com");
            Bookmark bm2 = new Bookmark("english learning",
                    "https://www.easyenglish.com");
            Bookmark bm3 = new Bookmark("mandarin learning",
                    "https://www.easymandarin.com");

            List<Bookmark> bookmarks = Arrays.asList(bm1, bm2);

            repository.save(bm3);
            Folder f1 = new Folder("folder1",
                    "testing folder1",
                    bookmarks);

            folderRepository.save(f1);


        };

    }



}
