package edu.radyuk.xml.exception;

public class RailwayCarriageException extends Exception {
    public RailwayCarriageException() {

    }

    public RailwayCarriageException(String message) {
        super(message);
    }

    public RailwayCarriageException(String message, Throwable cause) {
        super(message, cause);
    }

    public RailwayCarriageException(Throwable cause) {
        super(cause);
    }
}
