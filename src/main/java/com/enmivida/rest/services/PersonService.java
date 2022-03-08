package com.enmivida.rest.services;

import com.enmivida.rest.data.model.Person;
import com.enmivida.rest.data.vo.PersonVO;
import com.enmivida.rest.exception.ResourceNotFoundException;
import com.enmivida.rest.repository.PersonRepository;
import com.enmivida.rest.utils.mapper.PersonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository repository;
    private final PersonMapper personMapper;

    public PersonVO findById(Long id) {
        Person person = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
        return personMapper.personToPersonVO(person);
    }

    public Page<PersonVO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(personMapper::personToPersonVO);
    }

    public Page<PersonVO> findPersonByName(String firstName, Pageable pageable) {
        return repository.findPersonByFirstNameContaining(firstName, pageable).map(personMapper::personToPersonVO);
    }

    public PersonVO create(PersonVO personVO) {
        Person person = personMapper.personVOToPerson(personVO);
        person = repository.save(person);
        return personMapper.personToPersonVO(person);

    }

    public PersonVO update(PersonVO personVO) {
        Person entity = repository.findById(personVO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
        entity.setFirstName(personVO.getFirstName());
        entity.setLastName(personVO.getLastName());
        entity.setAddress(personVO.getAddress());
        entity.setGender(personVO.getGender());

        return personMapper.personToPersonVO(repository.save(entity));
    }

    public void deleteById(Long id) {
        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
        repository.delete(entity);
    }
}
