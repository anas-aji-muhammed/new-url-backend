package com.anasajimuhammed.newurl.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class ClickEvents {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "url_id")
    private Long urlId; // Assume this links to your URL entity

    private LocalDateTime clickedAt;
    @PrePersist
    protected void onCreate() {
        clickedAt = LocalDateTime.now();
    }

}
