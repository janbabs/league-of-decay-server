package com.janbabs.leagueofdecayserver.exception;

import java.io.FileNotFoundException;

public class NoMatchListException extends Exception{
    public NoMatchListException() {
    }

    public NoMatchListException(String message) {
        super(message);
    }
}
