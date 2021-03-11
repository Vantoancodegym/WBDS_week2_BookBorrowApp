package vantoan.service.bookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vantoan.model.Book;
import vantoan.repository.BookRepository;

import java.util.List;

@Service
public class BookServie implements IBookService{
    @Autowired
    private BookRepository bookRepository;
    @Override
    public List<Book> findAll() {
        return (List<Book>) bookRepository.findAll();
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findOne(id);
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }
}
