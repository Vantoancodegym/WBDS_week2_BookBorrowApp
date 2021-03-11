package vantoan.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class BorrowCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Book book;
    private int borrowCode;
    private LocalDate borrowDate;

    public BorrowCode() {
    }

    public BorrowCode( Book book, int borrowCode, LocalDate borrowDate) {
        this.book = book;
        this.borrowCode = borrowCode;
        this.borrowDate = borrowDate;
    }

    public int getBorrowCode() {
        return borrowCode;
    }

    public void setBorrowCode(int borrowCode) {
        this.borrowCode = borrowCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }
}
