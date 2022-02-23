package com.enmivida.rest.controller;

import com.enmivida.rest.data.vo.PersonVO;
import com.enmivida.rest.services.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService service;

    @GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
    public ResponseEntity<PersonVO> findPersonById(@PathVariable("id") Long id) {
        PersonVO person = service.findById(id);
        Link link = linkTo(methodOn(PersonController.class).findPersonById(id)).withSelfRel();
        person.add(link);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<List<PersonVO>> findAllPeople() {
        List<PersonVO> people = service.findAll();

        people.stream().forEach(personVO -> {
            Link link = linkTo(methodOn(PersonController.class).findPersonById(personVO.getId())).withSelfRel();
            personVO.add(link);
        });

        return new ResponseEntity<>(people, HttpStatus.OK);
    }

    @PostMapping(consumes = {"application/json", "application/xml", "application/x-yaml"},
            produces = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<PersonVO> createPerson(@RequestBody PersonVO person) {
        PersonVO result = service.create(person);
        Link link = linkTo(methodOn(PersonController.class).findPersonById(person.getId())).withSelfRel();
        person.add(link);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping(consumes = {"application/json", "application/xml", "application/x-yaml"},
            produces = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<PersonVO> updatePerson(@RequestBody PersonVO person) {
        PersonVO result = service.update(person);
        Link link = linkTo(methodOn(PersonController.class).findPersonById(person.getId())).withSelfRel();
        person.add(link);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePersonById(@PathVariable("id") Long id) {
        service.deleteById(id);
    }
}
