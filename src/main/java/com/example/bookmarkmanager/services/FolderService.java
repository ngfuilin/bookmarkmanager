package com.example.bookmarkmanager.services;

import com.example.bookmarkmanager.exception.ResourceNotFoundException;
import com.example.bookmarkmanager.model.Folder;
import com.example.bookmarkmanager.repositories.FolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class FolderService {

    @Autowired
    public final FolderRepository folderRepository;

    public FolderService(FolderRepository folderRepository) {
        this.folderRepository = folderRepository;
    }

    public List<Folder> getFolders() {

        return folderRepository.findAll();
    }

    public void addNewFolder(Folder folder) {

        //Optional<Folder> folderOptional = folderRepository.findFolderByName(folder.getName());
        //if (folderOptional.isPresent()) {
        //    throw new IllegalStateException(
        //            "Folder with name = " + folder.getName() + " is exists");
        //}
        folderRepository.save(folder);
    }

    public void deleteFolder(Long folderId) {
        boolean exists = folderRepository.existsById(folderId);
        if (!exists) {
            throw new ResourceNotFoundException(
                    "Folder with id = " + folderId + " does not exists");
        }

       folderRepository.deleteById(folderId);
    }

    @Transactional
    public void updateFolder(Long id, String name, String description) {
        Folder folder = folderRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Folder with id = " + id + " does not exist"));

        //Optional<Folder> optionalFolder = folderRepository.findFolderByName(name);
        //if (optionalFolder.isPresent() &&
         //       !Objects.equals(optionalFolder.get().getId(), id)) {
          //  throw new IllegalStateException("Folder name = " + name + " belongs to folder id = " + optionalFolder.get().getId());
       // }

        if (name != null &&
                name.length() > 0 &&
                !Objects.equals(folder.getName(), name)) {
            folder.setName(name);
        }

        if (description != null &&
           description.length() > 0 &&
                !Objects.equals(folder.getDescription(), description)) {
            folder.setDescription(description);
        }


    }


}
