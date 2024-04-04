package com.anasajimuhammed.newurl.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class AggregatedClickEvents {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long urlId;

    private LocalDate date;

    private Long count;

    private Long linkClicks;

    private Long qrScans;

}
