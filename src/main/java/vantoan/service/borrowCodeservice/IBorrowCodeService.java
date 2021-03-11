package vantoan.service.borrowCodeservice;

import vantoan.model.Book;
import vantoan.model.BorrowCode;

public interface IBorrowCodeService {
    BorrowCode findById(Long id);
    BorrowCode findByCode(int code);
    BorrowCode save(BorrowCode borrowCode);
    void delete(BorrowCode borrowCode);
}
