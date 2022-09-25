package com.example.bookmarkmanager.controllers;


import com.example.bookmarkmanager.model.Bookmark;
import com.example.bookmarkmanager.services.BookmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path="/api/v1/bookmarks")
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @Autowired
    public BookmarkController(BookmarkService bookmarkService) {

        this.bookmarkService = bookmarkService;
    }

    @GetMapping
    public List<Bookmark> getBookmarks() {
        return bookmarkService.getBookmarks();
    }

   @GetMapping(path ="/folders/{folderId}")
    public List<Bookmark> getBookmarksByFolderId(
            @PathVariable("folderId") Long folderId) {
       return bookmarkService.getBookmarksByFolderId(folderId);
   }

    @DeleteMapping(path="{id}")
    public void deleteBookmark(@PathVariable("id") Long id) {
        bookmarkService.deleteBookmark(id);
    }

    @PostMapping
    public void addNewBookmark(@RequestBody Bookmark bookmark) {
           bookmarkService.addNewBookmark(bookmark);
    }

    @PutMapping(path = "{bookmarkId}")
   public void updateBookmark(
            @PathVariable("bookmarkId") Long bookmarkId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String url,
            @RequestParam(required = false) Long folderId
    )
    {
        bookmarkService.updateBookmark(bookmarkId, name, url, folderId);

    };
}