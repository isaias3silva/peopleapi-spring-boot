package br.com.dio.personapi.utils;

import br.com.dio.personapi.dto.request.PersonDTO;
import br.com.dio.personapi.entity.Person;
import org.junit.platform.commons.util.CollectionUtils;

import java.time.LocalDate;
import java.util.Collections;

public class PersonUtils {
    private static final String FIRST_NAME = "Rodrigo";
    private static final String LAST_NAME = "Peleias";
    private static final String CPF_NUMBER = "369.333.878-79";
    private static final Long PERSON_ID = 1L;
   public static final LocalDate BIRTH_DATE = LocalDate.of(2010,10,1);

   public static PersonDTO createFakeDTO() {
       return PersonDTO.builder()
               .fistName(FIRST_NAME)
               .lastName(LAST_NAME)
               .cpf(CPF_NUMBER)
               .birthDate("04-04-2010")
               .phones(Collections.singletonList(PhoneUtils.createFakeDTO()))
               .build();
   }
   public static Person createFakeEntity(){
       return Person.builder()
               .id(PERSON_ID)
               .fistName(FIRST_NAME)
               .lastName(LAST_NAME)
               .cpf(CPF_NUMBER)
               .birthDate(BIRTH_DATE)
               .phones(Collections.singletonList(PhoneUtils.createFakeEntity()))
               .build();
   }
}