package nl.jvb.mypaintpholio.exceptions;

public class UsernameNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UsernameNotFoundException(String username) {
        super("Gebruiker wordt niet gevonden.");
    }
}
