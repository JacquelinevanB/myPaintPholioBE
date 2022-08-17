package nl.jvb.mypaintpholiobe.controllers;

import nl.jvb.mypaintpholiobe.domain.dtos.QuoteTextDto;
import nl.jvb.mypaintpholiobe.domain.entities.QuoteText;
import nl.jvb.mypaintpholiobe.services.QuoteTextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/quotes")
public class QuoteTextController {

    private final QuoteTextService quoteTextService;

    @Autowired
    public QuoteTextController(QuoteTextService quoteTextService) {
        this.quoteTextService = quoteTextService;
    }

    @GetMapping("/admin")
    public ResponseEntity<List<QuoteTextDto>> getAllQuotes() {
        List<QuoteTextDto> allQuotes = quoteTextService.getAllQuotes();
        return ResponseEntity.ok().body(allQuotes);
    }

    @GetMapping("/random")
    public ResponseEntity<QuoteTextDto> getRandomQuote() {
        QuoteTextDto randomQuote = quoteTextService.getRandomQuote();
        return ResponseEntity.ok().body(randomQuote);
    }

    @PostMapping("/add_quote")
    public ResponseEntity<QuoteTextDto> addQuote(@RequestBody QuoteTextDto quoteTextDto) {
        final QuoteTextDto newQuote = quoteTextService.addQuoteText(quoteTextDto);
        final URI location = URI.create("/quotes/" + newQuote.getId());
        return ResponseEntity.created(location).body(newQuote);
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuoteTextDto> updateQuote(@PathVariable("id") Long id,
                                              @RequestBody QuoteTextDto quoteTextDto) {
        QuoteTextDto update = quoteTextService.updateQuoteText(id, quoteTextDto);
        return ResponseEntity.ok().body(update);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<QuoteText> deleteQuoteText(@PathVariable("id") Long id) {
        quoteTextService.deleteQuoteText(id);
        return ResponseEntity.noContent().build();
    }
}
