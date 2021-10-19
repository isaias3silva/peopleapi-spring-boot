package br.com.dio.personapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PersonNotFoundExceptions extends Exception {
    public PersonNotFoundExceptions(Long id) {
        super("Person not found with ID " + id);
    }
}
