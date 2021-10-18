package br.com.dio.personapi.controller;

import br.com.dio.personapi.dto.response.MessageResponseDTO;
import br.com.dio.personapi.entity.Person;
import br.com.dio.personapi.repository.PersonRepository;
import br.com.dio.personapi.service.PersonService;
import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@RequestBody Person person){
        return personService.createPerson(person);
    }
}
