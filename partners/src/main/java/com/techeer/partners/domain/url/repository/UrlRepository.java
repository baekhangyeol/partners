package com.techeer.partners.domain.url.repository;

import com.techeer.partners.domain.url.domain.Url;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {
    Optional<Url> findByHash(String hash);
    Optional<Url> findByHashAndIsDeletedFalse(String hash);
    List<Url> findAllByIsDeletedFalseOrderByCreatedAtDesc();
    Optional<Url> findByOriginUrlAndIsDeletedFalse(String originalUrl);
}
