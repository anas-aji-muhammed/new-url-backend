package com.anasajimuhammed.newurl.repository;

import com.anasajimuhammed.newurl.models.AggregatedClickEvents;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AggregateClickEventsRepository extends JpaRepository<AggregatedClickEvents, Long> {
    Optional<AggregatedClickEvents> findByUrlIdAndDate(Long urlId, LocalDate date);
    Optional<List<AggregatedClickEvents>> findAllByUrlId(Long urlId);


}
