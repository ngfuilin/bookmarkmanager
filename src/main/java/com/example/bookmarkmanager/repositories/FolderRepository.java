package com.example.bookmarkmanager.repositories;

import com.example.bookmarkmanager.model.Bookmark;
import com.example.bookmarkmanager.model.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FolderRepository extends JpaRepository<Folder, Long> {

    @Query("SELECT s FROM Folder s WHERE s.name = ?1")
    Optional<Folder> findFolderByName(String name);





}
