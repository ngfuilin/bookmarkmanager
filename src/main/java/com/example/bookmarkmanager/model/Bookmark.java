package com.example.bookmarkmanager.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="bookmark")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Bookmark {

    @SequenceGenerator(
            name="bookmark_sequence",
            sequenceName = "bookmark_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(
            name = "id",
            updatable = false
    )
    @Id
    private Long id;
    @Column(name="name", nullable = false)
    private String name;
    @Column(name = "url", nullable = false)
    private String url;

     @CreationTimestamp
     @Column(name="created_at", nullable = false, updatable = false)
    private Date createdAt;

     @UpdateTimestamp
    @Column(name="updated_at")
    private Date updatedAt;

     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name = "folder_id", foreignKey = @ForeignKey(name="folder_id"))
     private Folder folder;

       public Bookmark() {
    }

    public Bookmark(Long id, String name, String url, Date createdAt, Date updatedAt, Folder folder) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.folder = folder;
    }

    public Bookmark(String name, String url) {
        this.name = name;
        this.url = url;

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

   public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Folder getFolder() {
        return folder;
    }

    public void setFolder(Folder folder) {
        this.folder = folder;
    }

    @Override
    public String toString() {
        return "Bookmark{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", folder=" + folder +
                '}';
    }
}