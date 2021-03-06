package br.com.dio.personapi.controller;

import br.com.dio.personapi.dto.request.PersonDTO;
import br.com.dio.personapi.dto.response.MessageResponseDTO;
import br.com.dio.personapi.exceptions.PersonNotFoundExceptions;
import br.com.dio.personapi.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/people")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonController {

    private PersonService personService;

    /*@Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }*/

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@RequestBody @Valid PersonDTO personDTO){
        return personService.create(personDTO);
    }
    @GetMapping
    public List<PersonDTO> listAll(){
       return personService.lisAll();
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PersonDTO findById(@PathVariable Long id) throws PersonNotFoundExceptions {
      return personService.findById(id);
    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponseDTO updateById(@PathVariable Long id, @RequestBody @Valid PersonDTO personDTO) throws PersonNotFoundExceptions {
        return personService.update(id, personDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws PersonNotFoundExceptions {
        personService.delete(id);
    }
}
