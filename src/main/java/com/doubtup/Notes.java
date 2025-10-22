package com.doubtup;

import jakarta.persistence.Column; // <--- NEW IMPORT
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "notes")
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Add this label
    @Column(unique = true)
    private String title;
    
    // --- NEW: Tell JPA this content can be very long ---
    @Column(columnDefinition = "TEXT") // <--- NEW LABEL
    private String content;

    // --- Getters and Setters (no change here) ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}