package nl.jvb.mypaintpholio.controllers;

import nl.jvb.mypaintpholio.domain.dtos.QuoteDto;
import nl.jvb.mypaintpholio.domain.entities.Quote;
import nl.jvb.mypaintpholio.services.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/quotes")
public class QuoteController {

    private final QuoteService quoteService;

    @Autowired
    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<QuoteDto>> getAllQuotes() {
        List<QuoteDto> allQuotes = quoteService.getAllQuotes();
        return ResponseEntity.ok().body(allQuotes);
    }

    @GetMapping("/random")
    public ResponseEntity<QuoteDto> getRandomQuote() {
        QuoteDto randomQuote = quoteService.getRandomQuote();
        return ResponseEntity.ok().body(randomQuote);
    }

    @PostMapping("/add_quote")
    public ResponseEntity<QuoteDto> addQuote(@RequestBody QuoteDto quoteDto) {
        final QuoteDto newQuote = quoteService.addQuote(quoteDto);
        final URI location = URI.create("/quotes/" + newQuote.getId());
        return ResponseEntity.created(location).body(newQuote);
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuoteDto> updateQuote(@PathVariable("id") Long id,
                                                @RequestBody QuoteDto quoteDto) {
        QuoteDto update = quoteService.updateQuote(id, quoteDto);
        return ResponseEntity.ok().body(update);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Quote> deleteQuote(@PathVariable("id") Long id) {
        quoteService.deleteQuote(id);
        return ResponseEntity.noContent().build();
    }
}
