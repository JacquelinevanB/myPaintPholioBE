package nl.jvb.mypaintpholiobe.exceptions;

import java.io.Serial;

public class RecordNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    private RecordNotFoundException() {
        super();
    }
    public RecordNotFoundException(String message) {

        super(message);
    }
}
