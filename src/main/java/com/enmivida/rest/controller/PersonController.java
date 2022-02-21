package com.enmivida.rest.controller;

import com.enmivida.rest.data.vo.PersonVO;
import com.enmivida.rest.services.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService service;

    @GetMapping("/{id}")
    public ResponseEntity<PersonVO> findPersonById(@PathVariable("id") Long id) {
        PersonVO person = service.findById(id);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PersonVO>> findAllPeople() {
        List<PersonVO> people = service.findAll();
        return new ResponseEntity<>(people, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PersonVO> createPerson(@RequestBody PersonVO person) {
        PersonVO result = service.create(person);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<PersonVO> updatePerson(@RequestBody PersonVO person) {
        PersonVO result = service.update(person);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePersonById(@PathVariable("id") Long id) {
        service.deleteById(id);
    }
}
