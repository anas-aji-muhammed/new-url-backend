package com.anasajimuhammed.newurl.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AggregateClickEventLocation {
    public enum locationTypes{
        Country,
        City
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String urlHash;
    private String location;

    @Enumerated(EnumType.STRING)
    private locationTypes locationType;

}
