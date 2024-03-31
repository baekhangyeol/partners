package com.techeer.partners.domain.url.dto.response;

import com.techeer.partners.domain.url.domain.Url;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetUrlListResponse {
    private Long id;
    private String originalUrl;
    private String shortUrl;
    private String hash;
    private LocalDateTime createdAt;

    public static GetUrlListResponse from(Url url) {
        return GetUrlListResponse.builder()
            .id(url.getId())
            .originalUrl(url.getOriginalUrl())
            .shortUrl(url.getShortUrl())
            .hash(url.getHash())
            .createdAt(url.getCreatedAt())
            .build();
    }

    public static List<GetUrlListResponse> listOf(List<Url> urls) {
        return urls.stream()
            .map(GetUrlListResponse::from)
            .collect(Collectors.toList());
    }
}
