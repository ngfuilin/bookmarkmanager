package com.example.bookmarkmanager.controllers;

import com.example.bookmarkmanager.model.Folder;
import com.example.bookmarkmanager.services.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/v1/folders")
public class FolderController {

    private final FolderService folderService;
    @Autowired
    public FolderController(FolderService folderService) {
        this.folderService = folderService;
    }

    @GetMapping
    public ResponseEntity<List<Folder>> getFolders() {

        List<Folder> folderList = folderService.getFolders();
        return new ResponseEntity<>(folderList, HttpStatus.OK);

    }

    @PutMapping(path="{id}")
    public ResponseEntity<HttpStatus> updateFolderById(@PathVariable("id") Long id,
                                 @RequestParam(required = true) String name,
                                 @RequestParam(required = false) String description) {
        folderService.updateFolder(id, name, description);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping(path="{id}")
    public ResponseEntity<HttpStatus> deleteFolder(
            @PathVariable("id") Long id) {
        folderService.deleteFolder(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> addNewFolder(
            @RequestBody Folder folder) {
        folderService.addNewFolder(folder);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
