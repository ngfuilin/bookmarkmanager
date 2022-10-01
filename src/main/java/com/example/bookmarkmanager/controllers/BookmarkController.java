package com.example.bookmarkmanager.controllers;


import com.example.bookmarkmanager.model.Bookmark;
import com.example.bookmarkmanager.services.BookmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path="/api/v1/bookmarks")
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @Autowired
    public BookmarkController(BookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
    }


   @GetMapping
    public ResponseEntity<List<Bookmark>> getBookmarks() {
        List<Bookmark> bookmarkList = bookmarkService.getBookmarks();

       return new ResponseEntity<>(bookmarkList, HttpStatus.OK);
    }



   @GetMapping(path ="/folders/{folderId}")
    public ResponseEntity<List<Bookmark>> getBookmarksByFolderId(
            @PathVariable("folderId") Long folderId) {
        List<Bookmark> bookmarkList =  bookmarkService.getBookmarksByFolderId(folderId);

       return new ResponseEntity<>(bookmarkList, HttpStatus.OK);
   }

    @DeleteMapping(path="{id}")
    public ResponseEntity<HttpStatus> deleteBookmark(@PathVariable("id") Long id) {
        bookmarkService.deleteBookmark(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> addNewBookmark(@RequestBody Bookmark bookmark) {
           bookmarkService.addNewBookmark(bookmark);
           return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(path = "{bookmarkId}")
   public ResponseEntity<HttpStatus> updateBookmark(
            @PathVariable("bookmarkId") Long bookmarkId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String url,
            @RequestParam(required = false) Long folderId
    )
    {
        bookmarkService.updateBookmark(bookmarkId, name, url, folderId);
        return new ResponseEntity<>(HttpStatus.OK);

    };
}