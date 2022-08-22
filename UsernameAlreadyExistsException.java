package nl.jvb.mypaintpholio.exceptions;

public class UsernameAlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UsernameAlreadyExistsException() {

        super();
    }

    public UsernameAlreadyExistsException(String message) {

        super(message);
    }
}