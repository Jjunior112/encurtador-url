package com.encurtador_url.application.services;

import com.encurtador_url.domain.models.Url;
import com.encurtador_url.infra.repositories.UrlRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;



@Service
public class UrlService {
    private final UrlRepository repository;

    private final RestTemplate restTemplate;

    public UrlService(UrlRepository repository, RestTemplate restTemplate) {
        this.repository = repository;
        this.restTemplate = restTemplate;
    }

    @Transactional
    public String generate(String fullUrl) {

        try {

            //Validar a URL recebida

            ResponseEntity<String> response = restTemplate.getForEntity(fullUrl, String.class);

            //Criar a entidade,salvar e retornar id

            Url url = new Url();
            String id;

            do {
                id = RandomStringUtils.randomAlphanumeric(5, 10);
            }
            while (repository.existsById(id));

            url.setId(id);

            url.setFullUrl(fullUrl);

            repository.save(url);

            return id;

        } catch (ResourceAccessException e) {

            //Exception para erro na resposta (recurso inválido)

            throw new IllegalArgumentException("Não foi possível acessar a URL: " + fullUrl);
        } catch (Exception e) {

            //Exception genérica

            throw new IllegalArgumentException("Erro inesperado ao acessar a URL: " + fullUrl);
        }

    }

    public String getUrl(String id) {
        var url = repository.findById(id);

        if (url.isEmpty()) {
            throw new NullPointerException();
        }

        return url.get().getFullUrl();
    }

}

