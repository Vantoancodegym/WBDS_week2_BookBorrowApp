package vantoan.service.borrowCodeservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vantoan.model.Book;
import vantoan.model.BorrowCode;
import vantoan.repository.BookRepository;
import vantoan.repository.BorrowCodeRepository;

@Service
public class BorrowCodeService implements IBorrowCodeService{
    @Autowired
    private BorrowCodeRepository borrowCodeRepository;
    @Override
    public BorrowCode findById(Long id) {
        return borrowCodeRepository.findOne(id);
    }

    @Override
    public BorrowCode findByCode(int code) {
        return borrowCodeRepository.findBorrowCodeByCodeQ(code);
    }

    @Override
    public void delete(BorrowCode borrowCode) {
        borrowCodeRepository.delete(borrowCode);

    }

    @Override
    public BorrowCode save(BorrowCode borrowCode) {
        return borrowCodeRepository.save(borrowCode);
    }
}
