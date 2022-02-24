package com.enmivida.rest.controller;

import com.enmivida.rest.data.vo.BookVO;
import com.enmivida.rest.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {

    private final BookService service;

    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml"})
    public ResponseEntity<BookVO> findBookById(@PathVariable("id") Long id) {
        BookVO book = service.findById(id);
        Link link = linkTo(methodOn(BookController.class).findBookById(id)).withSelfRel();
        book.add(link);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping(produces = {"application/json", "application/xml"})
    public ResponseEntity<List<BookVO>> findAllBooks() {
        List<BookVO> books = service.findAll();
        books.stream().forEach(bookVO -> {
            Link link = linkTo(methodOn(BookController.class).findBookById(bookVO.getId())).withSelfRel();
            bookVO.add(link);
        });
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
}
