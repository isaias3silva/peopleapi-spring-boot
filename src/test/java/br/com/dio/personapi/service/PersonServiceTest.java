package br.com.dio.personapi.service;

import br.com.dio.personapi.dto.request.PersonDTO;
import br.com.dio.personapi.dto.response.MessageResponseDTO;
import br.com.dio.personapi.entity.Person;
import br.com.dio.personapi.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.dio.personapi.utils.PersonUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {
    @Mock
    private PersonRepository personRepository;
    @InjectMocks
    private PersonService personService;

    @Test
    void testGivenPersonDTOThenReturnSavedMessage() {
        PersonDTO personDTO = createFakeDTO();
        Person expectedSavedPerson = createFakeEntity();

       when(personRepository.save(any(Person.class))).thenReturn(expectedSavedPerson);
       MessageResponseDTO expectedSuccessMessage = createExpectedMessageResponse(expectedSavedPerson.getId());
       MessageResponseDTO successMessage = personService.createPerson(personDTO);

       assertEquals(expectedSuccessMessage, successMessage);
    }
    private MessageResponseDTO createExpectedMessageResponse(Long id){
        return MessageResponseDTO
                .builder()
                .message("Created person with ID " + id)
                .build();
    }
}
