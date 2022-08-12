package nl.jvb.mypaintpholiobe.exceptions;

public class UsernameNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UsernameNotFoundException(String username) {
        super("User '" + username + "' cannot be found.");
    }
}
