package com.encurtador_url.application.services;

import com.encurtador_url.domain.models.Url;
import com.encurtador_url.infra.repositories.UrlRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Console;


@Service
public class UrlService {
    private final UrlRepository repository;

    public UrlService(UrlRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public String generate(String fullUrl) {
        Url url = new Url();
        String id;

        do {
            id = RandomStringUtils.randomAlphanumeric(5, 10);
        }
        while (repository.existsById(id));

        url.setId(id);

        url.setFullUrl(fullUrl);

        repository.save(url);

        return url.getId();
    }

    public String getUrl(String id) {
        var url = repository.findById(id);

        if (url.isEmpty()) {
            throw new NullPointerException();
        }

        return url.get().getFullUrl();
    }

}

