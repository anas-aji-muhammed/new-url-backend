package com.anasajimuhammed.newurl.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "url_table")
public class URLModel {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private String originalURL;

    private String shortURL;

    private String urlHash;

    private Boolean isActive;

    private Long count;

    private LocalDateTime createdDateTime;

    @PrePersist
    protected void onCreate() {
        createdDateTime = LocalDateTime.now();
    }



}
