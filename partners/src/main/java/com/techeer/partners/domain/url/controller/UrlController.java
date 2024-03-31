package com.techeer.partners.domain.url.controller;

import com.techeer.partners.domain.url.dto.request.UrlCreateRequest;
import com.techeer.partners.domain.url.dto.response.GetUrlListResponse;
import com.techeer.partners.domain.url.dto.response.UrlCreateResponse;
import com.techeer.partners.domain.url.service.UrlService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/short-links")
@AllArgsConstructor
public class UrlController {
    private final UrlService urlService;

    @PostMapping
    public ResponseEntity<UrlCreateResponse> createShortUrl(@RequestBody UrlCreateRequest request) {
        UrlCreateResponse response = urlService.createShortUrl(request);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(response);
    }

    @GetMapping("/{hash}")
    public RedirectView redirectToOriginalUrl(@PathVariable String hash) {
        String originalUrl = urlService.getOriginalUrlByHash(hash)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하는 URL을 찾을 수 없습니다."));
        return new RedirectView(originalUrl);
    }

    @GetMapping
    public ResponseEntity<List<GetUrlListResponse>> getUrlList() {
        return ResponseEntity.status(HttpStatus.OK)
            .body(urlService.getUrlList());
    }

    @DeleteMapping("/{hash}")
    public ResponseEntity<Void> deleteShortUrl(@PathVariable String hash) {
        urlService.deleteShortUrl(hash);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
            .build();
    }
}
