package com.enmivida.rest.utils.mapper;

import com.enmivida.rest.data.model.Book;
import com.enmivida.rest.data.vo.BookVO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface BookMapper {
    Book toBook(BookVO bookVO);
    BookVO toBookVO(Book book);
    List<BookVO> toBookVOList(List<Book> bookList);
}
