package com.encurtador_url.infra.repositories;

import com.encurtador_url.domain.models.Url;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UrlRepository extends MongoRepository<Url, String> {

}
