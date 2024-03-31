package com.techeer.partners.domain.url.domain;

import com.techeer.partners.global.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Url extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String originalUrl;
    @Column(nullable = false)
    private String shortUrl;
    @Column(unique = true)
    private String hash;
}
