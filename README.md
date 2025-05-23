# 🔐 Secure Microservices with Spring Boot, OAuth2, and Docker

Detta projekt demonstrerar en säker mikrotjänst-arkitektur med Spring Boot, OAuth2 Authorization Server, Gateway, Resource Servers samt Docker och Consul för service discovery.

## 🧱 Arkitekturöversikt

[SPA/BFF/Insomnia] --> [Gateway] --> [Resource Servers]
|
[Consul]
|
[OAuth2 Auth Server]

yaml
Copy
Edit

## 🧪 Funktioner

- ✅ OAuth2 Authorization Server (med PKCE) på port `9000`
- ✅ Resource Servers med JWT-skyddade endpoints (`/secure`, `/jokes/random`)
- ✅ API Gateway som vidarebefordrar trafik till backend
- ✅ Service discovery med Consul
- ✅ Docker Compose för enkel uppstart

---

## 🐳 Kom igång

### 1. Starta systemet
```bash
docker-compose up --build
2. Logga in via Authorization Server
Gå till: http://localhost:9000/auth/login
Användare: user
Lösenord: password

3. Hämta JWT-token (PKCE)
Följ OAuth2-flödet via exempelvis Insomnia eller webbläsare (Authorization Code med PKCE). Spara access_token.

4. Anropa säkrad endpoint
h
Copy
Edit
GET http://localhost:8080/api/secure
Authorization: Bearer <access_token>
http
Copy
Edit
GET http://localhost:8080/jokes/random
Authorization: Bearer <access_token>
🛠 Tjänster
Tjänst	Port	Beskrivning
Auth Server	9000	OAuth2 Login + Tokenutgivning
Gateway	8080	API Gateway
ResourceServer	8081	Skyddade /secure, /public
JokeService	8082	JWT-skyddad /jokes/random
Consul	8500	Service Discovery UI

🔐 Säkerhet
Authorization Server använder spring-authorization-server

JWT-tokens signeras dynamiskt via JWK

Resource Servers validerar token med issuer-uri=http://localhost:9000

Endast autentiserade användare får åtkomst till skyddade endpoints

📦 Exempel på JWT-token payload
json
Copy
Edit
{
  "sub": "user",
  "scope": ["read_resource"],
  "roles": ["ROLE_USER"],
  "iss": "http://localhost:9000",
  "exp": 1716463500
}
👨‍💻 Utvecklat med
Spring Boot 3

Spring Security + Authorization Server

Spring Cloud Gateway

Docker + Docker Compose

Consul för service discovery

📝 TODO (om vidareutveckling önskas)
 Lägga till användarregistrering

 Persistens av tokens och klienter

 Lägga till Refresh Tokens

 Mer avancerad rollstyrning

📚 Labbkrav uppfyllda ✅
Krav	Status
OAuth2 Authorization Code + PKCE	✅
JWT-skyddade Resource Servers	✅
Gateway som skyddar och vidarebefordrar	✅
Docker Compose	✅
Service Discovery via Consul	✅
Insomnia-test med token	✅

