package com.enmivida.rest.services;

import com.enmivida.rest.data.model.Book;
import com.enmivida.rest.data.vo.BookVO;
import com.enmivida.rest.exception.ResourceNotFoundException;
import com.enmivida.rest.repository.BookRepository;
import com.enmivida.rest.utils.mapper.BookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;
    private final BookMapper mapper;

    public List<BookVO> findAll() {
        List<Book> books = repository.findAll();
        return mapper.toBookVOList(books);
    }

    public BookVO findById(Long id) {
        Book book = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
        return mapper.toBookVO(book);
    }
}
