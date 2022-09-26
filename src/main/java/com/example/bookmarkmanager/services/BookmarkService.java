package com.example.bookmarkmanager.services;

import com.example.bookmarkmanager.model.Bookmark;
import com.example.bookmarkmanager.model.Folder;
import com.example.bookmarkmanager.repositories.BookmarkRepository;
import com.example.bookmarkmanager.repositories.FolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookmarkService {

    private BookmarkRepository bookmarkRepository;

    private FolderRepository folderRepository;

   @Autowired
    public BookmarkService(BookmarkRepository bookmarkRepository, FolderRepository folderRepository) {
        this.bookmarkRepository = bookmarkRepository;
        this.folderRepository = folderRepository;
    }

    public List<Bookmark> getBookmarks() {
        return bookmarkRepository.findAll();
    }

    public List<Bookmark> getBookmarksByFolderId(Long folderId) {

        return bookmarkRepository.findBookmarksByFolder(folderId);
    }

    public void deleteBookmark(Long bookmarkId) {
        boolean exists = bookmarkRepository.existsById(bookmarkId);
        if (!exists) {
            throw new IllegalStateException(
                    "Bookmark with id = " + bookmarkId + " does not exists");
        }
        bookmarkRepository.deleteById(bookmarkId);

    }

    public void addNewBookmark(Bookmark bookmark) {

        //Optional<Bookmark> bookmarkOptional = bookmarkRepository.findBookmarkByName(bookmark.getName());
       // if (bookmarkOptional.isPresent()) {
        //    throw new IllegalStateException(
        //            "Bookmark with name = " + bookmark.getName() + " is exists");
       // }

       if (bookmark.getFolder() == null) {
            bookmarkRepository.save(bookmark);
        } else {
            if (bookmark.getFolder().getId() == null) {
                throw new IllegalStateException(
                        "Invalid JSON format"
                );
            }

            boolean exists = folderRepository.existsById(bookmark.getFolder().getId());
            if (!exists) {
                throw new IllegalStateException(
                        "Folder id = " + bookmark.getFolder().getId() + " does not exists");
            }
            Folder folder = folderRepository.findById(bookmark.getFolder().getId()).orElse(null);
            if (null == folder) {
                folder = new Folder();
            }
            folder.setId(bookmark.getFolder().getId());
            bookmark.setFolder(folder);
            bookmarkRepository.save(bookmark);
        }
    }

    @Transactional
    public void updateBookmark(Long bookmarkId,
                               String name,
                               String url,
                               Long folderId) {
        Bookmark bookmark = bookmarkRepository.findById(bookmarkId).
                orElseThrow(() -> new IllegalStateException("Bookmark with id = " + bookmarkId + "does not exists"));

       // Optional<Bookmark> bookmark1 = bookmarkRepository.findBookmarkByName(name);
        //if (bookmark1.isPresent() &&
         //       !Objects.equals(bookmark1.get().getId(), bookmarkId))
          //     {
           // throw new IllegalStateException("Bookmark name =" + name + " belongs to bookmark id = " + bookmark1.get().getId());
       // }

       if (folderId != null ) {
            Folder folder = folderRepository.findById(folderId).
                    orElseThrow(() -> new IllegalStateException("Folder id = " + folderId + "does not exist"));
            bookmark.setFolder(folder);
        }

        if (name != null &&
                name.length() > 0 &&
                !Objects.equals(bookmark.getName(), name)) {
            bookmark.setName(name);
        }

        if (url != null &&
                url.length() > 0 &&
                !Objects.equals(bookmark.getUrl(), url)) {
            bookmark.setUrl(url);
        }

    }

}