package nl.jvb.mypaintpholio.domain.dtos;

public class QuoteDto {

    private Long id;

    private String text;
    private String source;

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getSource() {
        return source;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
