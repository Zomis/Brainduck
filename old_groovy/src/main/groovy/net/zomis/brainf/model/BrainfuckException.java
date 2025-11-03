package net.zomis.brainf.model;

public class BrainfuckException extends Exception {

    public BrainfuckException() {
    }

    public BrainfuckException(String message) {
        super(message);
    }

    public BrainfuckException(String message, Throwable cause) {
        super(message, cause);
    }

    public BrainfuckException(Throwable cause) {
        super(cause);
    }

}
