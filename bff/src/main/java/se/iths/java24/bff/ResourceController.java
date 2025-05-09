package se.iths.java24.bff;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.logging.Logger;

@RestController
public class ResourceController {

    private final OAuth2AuthorizedClientService clientService;
    private final WebClient webClient;

    private static final Logger logger = Logger.getLogger(ResourceController.class.getName());

    public ResourceController(OAuth2AuthorizedClientService clientService) {
        this.clientService = clientService;
        this.webClient = WebClient.builder().build();
    }

    @GetMapping("/api/proxy")
    public Mono<String> proxyResource(@AuthenticationPrincipal OAuth2User principal,
                                      @RegisteredOAuth2AuthorizedClient("my-auth-server") OAuth2AuthorizedClient client,
                                      HttpServletRequest request) {

        String clientIp = request.getRemoteAddr();

        logger.info("Client IP: " + clientIp);
        logger.info("Principal: " + principal.getName());
        logger.info("Client ID (Refresh Token): " + client.getRefreshToken());

        return webClient.get()
                .uri("http://localhost:8080/secure")
                .headers(h -> {
                    h.setBearerAuth(client.getAccessToken().getTokenValue());
                    h.set("X-Forwarded-For", clientIp); // set in forwarded IP
                })
                .retrieve()
                .bodyToMono(String.class);
    }


}
