package nl.jvb.mypaintpholiobe.domain.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class QuoteText {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String quoteText;
    private String source;

    public Long getId() {
        return id;
    }

    public String getQuoteText() {
        return quoteText;
    }

    public String getSource() {
        return source;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setQuoteText(String quoteText) {
        this.quoteText = quoteText;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
