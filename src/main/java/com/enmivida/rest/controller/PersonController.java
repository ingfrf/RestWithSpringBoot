package com.enmivida.rest.controller;

import com.enmivida.rest.data.vo.PersonVO;
import com.enmivida.rest.services.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService service;
    private final PagedResourcesAssembler<PersonVO> pagedResourcesAssembler;

    @GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
    public ResponseEntity<PersonVO> findPersonById(@PathVariable("id") Long id) {
        PersonVO person = service.findById(id);
        Link link = linkTo(methodOn(PersonController.class).findPersonById(id)).withSelfRel();
        person.add(link);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<PagedModel<PersonVO>> findAllPeople(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "12") int limit,
            @RequestParam(value = "direction", defaultValue = "asc") String direction) {

        Sort.Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "firstName"));

        Page<PersonVO> people = service.findAll(pageable);

        people.stream().forEach(personVO -> {
            Link link = linkTo(methodOn(PersonController.class).findPersonById(personVO.getId())).withSelfRel();
            personVO.add(link);
        });

        return new ResponseEntity(pagedResourcesAssembler.toModel(people), HttpStatus.OK);
    }

    @GetMapping(value = "/findPersonByName/{firstName}", produces = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<PagedModel<PersonVO>> findPersonByFirstName(
            @PathVariable("firstName") String firstName,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "12") int limit,
            @RequestParam(value = "direction", defaultValue = "asc") String direction) {

        Sort.Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "firstName"));

        Page<PersonVO> people = service.findPersonByName(firstName, pageable);

        people.stream().forEach(personVO -> {
            Link link = linkTo(methodOn(PersonController.class).findPersonById(personVO.getId())).withSelfRel();
            personVO.add(link);
        });

        return new ResponseEntity(pagedResourcesAssembler.toModel(people), HttpStatus.OK);
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
