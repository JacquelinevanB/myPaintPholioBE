package nl.jvb.mypaintpholio.services;

import nl.jvb.mypaintpholio.domain.dtos.QuoteDto;
import nl.jvb.mypaintpholio.domain.entities.Quote;
import nl.jvb.mypaintpholio.repositories.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuoteService {

    private final QuoteRepository quoteRepository;

    @Autowired
    public QuoteService(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    public List<QuoteDto> getAllQuotes() {
        List<Quote> quotes = quoteRepository.findAll();
        List<QuoteDto> dtoList = new ArrayList<>();
        for(Quote quote : quotes) {
            QuoteDto dto = quoteToDto(quote);
            dtoList.add(dto);
        }
        return dtoList;
    }

    public QuoteDto getRandomQuote() {
        Long qty = quoteRepository.count();
        int idx = (int)(Math.random() * qty);
        Page<Quote> quotePage = quoteRepository.findAll(PageRequest.of(idx, 1));
        Quote q = null;
        if (quotePage.hasContent()) {
            q = quotePage.getContent().get(0);
        }
        return quoteToDto(q);
    }

    public QuoteDto addQuote(QuoteDto quoteDto) {
        Quote quote = dtoToQuote(quoteDto);
        quoteRepository.save(quote);
        return quoteToDto(quote);
    }

    public QuoteDto updateQuote(Long id, QuoteDto quoteDto) {
        Quote quote = quoteRepository.findById(id).get();
        Quote updatedQuote = dtoToQuote(quoteDto);
        updatedQuote.setId(quote.getId());
        quoteRepository.save(updatedQuote);
        return quoteDto;
    }

    public void deleteQuote(Long id) {
        quoteRepository.deleteById(id);
    }

    public Quote dtoToQuote(QuoteDto dto) {
        Quote quote = new Quote();

        quote.setId(dto.getId());
        quote.setText(dto.getText());
        quote.setSource(dto.getSource());

        return quote;
    }

    public QuoteDto quoteToDto(Quote quote) {
        QuoteDto dto = new QuoteDto();

        dto.setId(quote.getId());
        dto.setText(quote.getText());
        dto.setSource(quote.getSource());

        return dto;
    }
}
