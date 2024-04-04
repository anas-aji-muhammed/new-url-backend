package com.anasajimuhammed.newurl.repository;

import com.anasajimuhammed.newurl.models.ClickEvents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ClickEventsRepository extends JpaRepository<ClickEvents, Long> {
    List<ClickEvents> findAllByUrlId(Long id);
    @Query("SELECT ce FROM ClickEvents ce WHERE ce.urlId = :urlId AND ce.clickedAt BETWEEN :startDate AND :endDate")
    List<ClickEvents> findClicksByUrlIdAndDateRange(@Param("urlId") Long urlId,
                                                    @Param("startDate") LocalDateTime startDate,
                                                    @Param("endDate") LocalDateTime endDate);
}
