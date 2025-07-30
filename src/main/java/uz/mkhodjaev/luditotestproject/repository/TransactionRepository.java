package uz.mkhodjaev.luditotestproject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.mkhodjaev.luditotestproject.model.Transaction;
import uz.mkhodjaev.luditotestproject.model.User;

import java.sql.Timestamp;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Page<Transaction> findAllByFromUserOrToUser(User fromUser, User toUser, Pageable pageable);
    Page<Transaction> findAllByTimestampBetween(Timestamp startDate, Timestamp endDate, Pageable pageable);
}
