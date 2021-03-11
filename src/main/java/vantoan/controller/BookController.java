package vantoan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vantoan.exception.NotFoundBookCode;
import vantoan.exception.OutOfStockException;
import vantoan.model.Book;
import vantoan.model.BorrowCode;
import vantoan.service.bookService.BookServie;
import vantoan.service.bookService.IBookService;
import vantoan.service.borrowCodeservice.BorrowCodeService;
import vantoan.service.borrowCodeservice.IBorrowCodeService;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/bookapp")
public class BookController {
    @Autowired
    private IBookService bookServie;
    @Autowired
    private IBorrowCodeService borrowCodeService;

    @ExceptionHandler(OutOfStockException.class)
    public ModelAndView showOutOfStockError(){
        return new ModelAndView("error","messError","this book is out of stock");
    }
    @ExceptionHandler(NotFoundBookCode.class)
    public ModelAndView showNotFoundBookCodeError(){
        return new ModelAndView("error","messError","your code is not exsit");
    }
    @GetMapping("")

    public ModelAndView showAll(){
        List<Book> list= bookServie.findAll();
        return new ModelAndView("home","list",list);
    }

    @GetMapping("borrow/{id}")
    public ModelAndView borrow(@PathVariable Long id) throws OutOfStockException {
        Book book =bookServie.findById(id);
        if (book.getAmount()<=0) throw new OutOfStockException();
        int newAmount= book.getAmount()-1;
        book.setAmount(newAmount);
        bookServie.save(book);
        createBookCode(book);
        return new ModelAndView("redirect:/bookapp");
    }

    private void createBookCode(Book book) {
        int borrow_Code=(int) (Math.random()*90000+10000);
        LocalDate borrowDate= LocalDate.now();
        BorrowCode borrowCode=new BorrowCode(book,borrow_Code,borrowDate);
        borrowCodeService.save(borrowCode);
    }
    @GetMapping("return/{id}")
    public ModelAndView showFormReturn(){
        return new ModelAndView("return");
    }
    @PostMapping("return/{id}")
    public ModelAndView returnBook(@PathVariable Long id, @RequestParam int bookCode) throws NotFoundBookCode {
        BorrowCode borrowCode=borrowCodeService.findByCode(bookCode);
        if (borrowCode==null) throw  new NotFoundBookCode();
        Book book= bookServie.findById(id);
        if (book.getId()!=(borrowCode.getBook().getId())) throw  new NotFoundBookCode();
        int newAmount= book.getAmount()+1;
        book.setAmount(newAmount);
        bookServie.save(book);
        borrowCodeService.delete(borrowCode);
        return new ModelAndView("redirect:/bookapp");
    }
}
