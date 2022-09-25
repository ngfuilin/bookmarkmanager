package com.example.bookmarkmanager.repositories;

import com.example.bookmarkmanager.model.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    @Query("SELECT b FROM Folder s JOIN s.bookmarks b where s.id = ?1")
   List<Bookmark> findBookmarksByFolder(Long folderId);

    @Query("SELECT s FROM Bookmark s WHERE s.url = ?1")
    Optional<Bookmark> findBookmarkByUrl(String url);

    @Query("SELECT s FROM Bookmark s WHERE s.name = ?1")
    Optional<Bookmark> findBookmarkByName(String name);
}
