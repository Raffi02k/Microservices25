package se.iths.java24.quoteservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController
public class QuoteController {

    private final List<String> quotes = List.of(
            "The greatest glory in living lies not in never falling, but in rising every time we fall. -Nelson Mandela",
            "The way to get started is to quit talking and begin doing. -Walt Disney",
            "Your time is limited, so don't waste it living someone else's life. -Steve Jobs",
            "If life were predictable it would cease to be life, and be without flavor. -Eleanor Roosevelt",
            "Life is what happens when you're busy making other plans. -John Lennon"
    );

    private final Random random = new Random();

    @GetMapping("/quotes/random")
    public String getRandomQuote() {
        return quotes.get(random.nextInt(quotes.size()));
    }
}
