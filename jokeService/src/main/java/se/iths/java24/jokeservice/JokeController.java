package se.iths.java24.jokeservice;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController
public class JokeController {

    private final List<String> jokes = List.of(
            "Why don't scientists trust atoms? Because they make up everything!",
            "I told my wife she was drawing her eyebrows too high. She seemed surprised.",
            "Why did the scarecrow win an award? Because he was outstanding in his field!"
    );

    private final Random random = new Random();

    @GetMapping("/jokes/random")
    @PreAuthorize("hasAuthority('SCOPE_jokes.read')")
    public String getRandomJoke() {
        return jokes.get(random.nextInt(jokes.size()));
    }
}
