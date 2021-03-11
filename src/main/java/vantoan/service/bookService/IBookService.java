package vantoan.service.bookService;

import vantoan.model.Book;

import java.util.List;

public interface IBookService {
    List<Book> findAll();
    Book findById(Long id);
    Book save(Book book);
}
