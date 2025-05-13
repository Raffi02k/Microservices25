package se.iths.java24.resourceserver;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @GetMapping("/secure")
    public String secureEndpoint() {
        return "Hello from the SECURE Resource Server! Token is valid.";
    }

    @GetMapping("/public")
    public String publicEndpoint(HttpServletRequest request) {
        String clinetIp = request.getRemoteAddr();
        int clientPort = request.getLocalPort();

        logger.info("Client IP: {} - Port: {}", clinetIp, clientPort);

        return "Hello from the PUBLIC Resource Server!";
    }
}
