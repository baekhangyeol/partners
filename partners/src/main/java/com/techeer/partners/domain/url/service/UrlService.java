package com.techeer.partners.domain.url.service;

import com.techeer.partners.domain.url.domain.Url;
import com.techeer.partners.domain.url.dto.request.UrlCreateRequest;
import com.techeer.partners.domain.url.dto.response.GetUrlListResponse;
import com.techeer.partners.domain.url.dto.response.CreateUrlResponse;
import com.techeer.partners.domain.url.repository.UrlRepository;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

@Service
@AllArgsConstructor
@Transactional
public class UrlService {
    private final UrlRepository urlRepository;

    public CreateUrlResponse createShortUrl(UrlCreateRequest request) {
        try {
            Optional<Url> existingUrl = urlRepository.findByOriginalUrl(request.getOriginalUrl());
            if (existingUrl.isPresent()) {
                return CreateUrlResponse.from(existingUrl.get());
            } else {
                String hash = DigestUtils.md5DigestAsHex(request.getOriginalUrl().getBytes()).substring(0, 6);

                Url entity = Url.builder()
                    .originalUrl(request.getOriginalUrl())
                    .shortUrl("http://localhost:8080/short-links/" + hash)
                    .hash(hash)
                    .build();
                Url url = urlRepository.save(entity);

                return CreateUrlResponse.from(url);
            }
        } catch (Exception e) {
            throw new RuntimeException("URL 생성 중 오류가 발생했습니다.", e);
        }
    }

    public Optional<String> getOriginalUrlByHash(String hash) {
        try {
            return urlRepository.findByHashAndIsDeletedFalse(hash)
                .map(Url::getOriginalUrl);
        } catch (Exception e) {
            throw new RuntimeException("해시로 원본 URL을 조회하는 중 오류가 발생했습니다.", e);
        }
    }


    public List<GetUrlListResponse> getUrlList() {
        List<Url> urls = urlRepository.findAllByIsDeletedFalseOrderByCreatedAtDesc();
        return GetUrlListResponse.listOf(urls);
    }

    public void deleteShortUrl(String hash) {
        try {
            Url url = urlRepository.findByHash(hash)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 URL 입니다."));

            url.delete();
        } catch (Exception e) {
            throw new RuntimeException("URL 삭제 중 오류가 발생했습니다.", e);
        }
    }
}
