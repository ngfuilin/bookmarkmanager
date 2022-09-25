package com.example.bookmarkmanager.controllers;

import com.example.bookmarkmanager.model.Folder;
import com.example.bookmarkmanager.services.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Folder> getFolders() {

        return folderService.getFolders();

    }

    @PutMapping(path="{id}")
    public void updateFolderById(@PathVariable("id") Long id,
                                 @RequestParam(required = true) String name,
                                 @RequestParam(required = false) String description) {
        folderService.updateFolder(id, name, description);

    }

    @DeleteMapping(path="{id}")
    public void deleteFolder(@PathVariable("id") Long id) {
        folderService.deleteFolder(id);
    }

    @PostMapping
    public void addNewFolder(@RequestBody Folder folder) {
        folderService.addNewFolder(folder);
    }
}
