package com.techeer.partners.domain.url.dto.response;

import com.techeer.partners.domain.url.domain.Url;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class CreateUrlResponse {
    private Long id;
    private String originalUrl;
    private String shortUrl;
    private String hash;
    private LocalDateTime createdAt;

    public static CreateUrlResponse from(Url url) {
        return CreateUrlResponse.builder()
            .id(url.getId())
            .originalUrl(url.getOriginalUrl())
            .shortUrl(url.getShortUrl())
            .hash(url.getHash())
            .createdAt(url.getCreatedAt())
            .build();
    }
}
