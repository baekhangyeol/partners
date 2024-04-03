package com.techeer.partners.domain.url.dto.request;

import com.techeer.partners.domain.url.domain.Url;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UrlCreateRequest {
    private String originUrl;

    public Url toEntity() {
        return Url.builder()
            .originUrl(originUrl)
            .build();
    }
}
