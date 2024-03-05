package com.anasajimuhammed.newurl.repository;

import com.anasajimuhammed.newurl.models.URLModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UrlStoreSQLRepository extends JpaRepository<URLModel, Long> {
    URLModel findByUrlHash(String hashUrl);

}
