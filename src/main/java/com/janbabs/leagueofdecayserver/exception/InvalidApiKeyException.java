package com.janbabs.leagueofdecayserver.exception;

import java.io.IOException;

public class InvalidApiKeyException extends IOException {
    public InvalidApiKeyException(String message) {
        super(message);
    }
}
