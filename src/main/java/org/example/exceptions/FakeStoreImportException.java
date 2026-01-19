package org.example.exceptions;

public class FakeStoreImportException extends RuntimeException {
    public FakeStoreImportException(String message) {
        super(message);
    }

    public FakeStoreImportException(String message, Throwable cause) {
        super(message, cause);
    }
}
