package com.encurtador_url.application.controllers;

import com.encurtador_url.application.services.UrlService;
import com.encurtador_url.domain.dtos.url.UrlRequestDto;
import com.encurtador_url.domain.dtos.url.UrlResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class UrlController {
    private final UrlService urlService;
    private final HttpServletRequest httpServletRequest;

    public UrlController(UrlService urlService, HttpServletRequest httpServletRequest) {
        this.urlService = urlService;
        this.httpServletRequest = httpServletRequest;
    }

    @PostMapping("/shorten-url")
    public ResponseEntity<UrlResponseDto> generateShortenUrl(@RequestBody UrlRequestDto requestDto) {
        var result = urlService.generate(requestDto.url());

        String redirectUrl = httpServletRequest.getRequestURL().toString().replace("shorten-url", result);

        return ResponseEntity.ok(new UrlResponseDto(redirectUrl));

    }

    @GetMapping("/{id}")
    public ResponseEntity<Void> getUrl(@PathVariable String id) {

        var url = urlService.getUrl(id);

        HttpHeaders headers = new HttpHeaders();

        headers.setLocation(URI.create(url));

        return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build();
    }

}
