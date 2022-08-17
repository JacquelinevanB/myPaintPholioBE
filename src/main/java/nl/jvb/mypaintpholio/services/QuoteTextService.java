package nl.jvb.mypaintpholio.services;

import nl.jvb.mypaintpholio.domain.dtos.QuoteTextDto;
import nl.jvb.mypaintpholio.domain.entities.QuoteText;
import nl.jvb.mypaintpholio.repositories.QuoteTextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuoteTextService {

    private final QuoteTextRepository quoteTextRepository;

    @Autowired
    public QuoteTextService(QuoteTextRepository quoteTextRepository) {
        this.quoteTextRepository = quoteTextRepository;
    }

    public List<QuoteTextDto> getAllQuotes() {
        List<QuoteText> quotes = quoteTextRepository.findAll();
        List<QuoteTextDto> dtoList = new ArrayList<>();
        for(QuoteText quote : quotes) {
            QuoteTextDto dto = quoteToDto(quote);
            dtoList.add(dto);
        }
        return dtoList;
    }

    public QuoteTextDto getRandomQuote() {
        Long qty = quoteTextRepository.count();
        int idx = (int)(Math.random() * qty);
        Page<QuoteText> quoteTextPage = quoteTextRepository.findAll(PageRequest.of(idx, 1));
        QuoteText q = null;
        if (quoteTextPage.hasContent()) {
            q = quoteTextPage.getContent().get(0);
        }
        return quoteToDto(q);
    }

    public QuoteTextDto addQuoteText(QuoteTextDto quoteTextDto) {
        QuoteText quote = dtoToQuote(quoteTextDto);
        quoteTextRepository.save(quote);
        return quoteToDto(quote);
    }

    public QuoteTextDto updateQuoteText(Long id, QuoteTextDto quoteTextDto) {
        QuoteText quote = quoteTextRepository.findById(id).get();
        QuoteText updatedQuote = dtoToQuote(quoteTextDto);
        updatedQuote.setId(quote.getId());
        quoteTextRepository.save(updatedQuote);
        return quoteTextDto;
    }

    public void deleteQuoteText(Long id) {
        quoteTextRepository.deleteById(id);
    }

    public QuoteText dtoToQuote(QuoteTextDto dto) {
        QuoteText quote = new QuoteText();

        quote.setId(dto.getId());
        quote.setQuoteText(dto.getQuoteText());
        quote.setSource(dto.getSource());

        return quote;
    }

    public QuoteTextDto quoteToDto(QuoteText quote) {
        QuoteTextDto dto = new QuoteTextDto();

        dto.setId(quote.getId());
        dto.setQuoteText(quote.getQuoteText());
        dto.setSource(quote.getSource());

        return dto;
    }
}
