package vantoan.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vantoan.model.BorrowCode;

@Repository
public interface BorrowCodeRepository extends CrudRepository<BorrowCode,Long> {
    @Query(value = "select b from BorrowCode as b where b.borrowCode =:code")
    BorrowCode findBorrowCodeByCodeQ(@Param("code") int code);
}
