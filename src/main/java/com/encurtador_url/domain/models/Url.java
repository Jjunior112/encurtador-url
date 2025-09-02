package com.encurtador_url.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "urls")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Url {
    @Id
    private String id;

    private String fullUrl;

    @Indexed(expireAfter= "0" )
    private LocalDateTime expiresAt = LocalDateTime.now().plusMinutes(10);
}
