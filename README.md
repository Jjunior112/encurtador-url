# 📦 Encurtador de URL com armazenamento em MongoDb

Este projeto é uma aplicação Java que gera URLs encurtadas a partir de entradas fornecidas pelo usuário e os armazena automaticamente em um banco de dados MongoDb

## Funcionalidades

- Encurta a URL fornecida pelo usuário com `POST` no endpoint `/shorten-url`
   - Request:
    ```json
      {
        "url":"https://example.com"
      }
    
    ```
    - Entidade salva:
    ```json
    {
      "id": "xA0dQm",
      "fullUrl": "https://example.com"
      "expiresAt":"2025-09-02T04:40:14.221+00:00"
    }
    
    ```

    - Response:  
    ```json
      {
        "url":"domain.com/xA0dQm"
      }
    
    ```

- Redireciona o usuário para URL completa com `GET` no endpoint `/{id}`
    ```http
    GET /xA0dQm
    Status: 302 Found
    Location: https://example.com
    ```

## Tecnologias Utilizadas

- Java 21+
- Maven
- MongoDb
- Docker (opcional)

## Estrutura do Projeto

```
encurtador-url/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com.encurtador_url/
│   │   │       ├── application/
│   │   │       │   └── controllers/
│   │   │       │   └── services/
│   │   │       ├── domain/
│   │   │       │   └── dtos/
│   │   │       │   └── models/
│   │   │       └── infra/       
│   │   │       │   └── repositories/
│   │   └── resources/
│   └── test/
├── .env
├── Dockerfile
├── compose.yaml
├── pom.xml
└── README.md

```

## Configuração

Antes de executar o projeto, configure as credenciais do MongoDb. Você pode fazer isso via variáveis de ambiente ou um arquivo `.env.development`.

Exemplo `.env.development`:

```env
MONGO_HOST=mongodb
MONGO_DATABASE_AUTHENTICATION=admin
MONGO_DATABASE=shortenerdb
MONGO_USERNAME=your_username
MONGO_PASSWORD=your_password

```

## Executando o Projeto

### Usando Docker
Usando o compose será criado um docker da aplicação e um do MongoDb que se comunicarão internamente no container

```bash
 sudo docker compose --env-file .env.development -f compose.yaml up
```
## Exemplo de Funcionamento

1. Usuário envia uma string contendo a URL.
2. A aplicação gera uma URL encurtada.
3. A URL é salva no MongoDB com `id` e `expiresAt` de 10 minutos (ajustável).
4. A URL é retornada de forma encurtada e quando acessada, redireciona para a URL original

## Implementações futuras

1. Controller advice para exceptions handlers
2. Validação de URLs
3. Monitoramento de acessos
4. Limite de acessos por URL
   
