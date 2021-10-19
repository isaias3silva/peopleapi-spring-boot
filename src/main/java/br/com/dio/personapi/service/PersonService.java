package br.com.dio.personapi.service;

import br.com.dio.personapi.dto.request.PersonDTO;
import br.com.dio.personapi.dto.response.MessageResponseDTO;
import br.com.dio.personapi.entity.Person;
import br.com.dio.personapi.exceptions.PersonNotFoundExceptions;
import br.com.dio.personapi.mapper.PersonMapper;
import br.com.dio.personapi.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

    private final PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    /*@Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }*/
    public MessageResponseDTO createPerson(PersonDTO personDTO){
        Person personToSave = personMapper.toModel(personDTO);
        Person savedPerson = personRepository.save(personToSave);
        return createMessageResponse(savedPerson.getId(), "Created person with ID ");
    }

    public List<PersonDTO> lisAll() {
        List<Person> allPeople = personRepository.findAll();
        return allPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundExceptions {
        Person person = verifyIfExists(id);
        return personMapper.toDTO(person);
    }

    public void delete(Long id) throws PersonNotFoundExceptions {
        verifyIfExists(id);
        personRepository.deleteById(id);
    }

    public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundExceptions {
        verifyIfExists(id);
        Person personToUpdate = personMapper.toModel(personDTO);

        Person updatedPerson = personRepository.save(personToUpdate);
        return createMessageResponse(updatedPerson.getId(), "Updated person with ID ");
    }

    private Person verifyIfExists(Long id) throws PersonNotFoundExceptions {
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundExceptions(id));
    }

    private MessageResponseDTO createMessageResponse(Long id, String s) {
        return MessageResponseDTO
                .builder()
                .message(s + id)
                .build();
    }
}
