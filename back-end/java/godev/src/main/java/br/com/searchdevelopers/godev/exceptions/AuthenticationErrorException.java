package br.com.searchdevelopers.godev.exceptions;

public class AuthenticationErrorException extends RuntimeException{
    public AuthenticationErrorException(String message) {
        super(message);
    }
}
