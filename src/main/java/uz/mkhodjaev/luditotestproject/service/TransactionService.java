package uz.mkhodjaev.luditotestproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.mkhodjaev.luditotestproject.dto.CreateTransactionDto;
import uz.mkhodjaev.luditotestproject.model.Transaction;
import uz.mkhodjaev.luditotestproject.model.User;
import uz.mkhodjaev.luditotestproject.repository.TransactionRepository;

import java.sql.Timestamp;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserService userService;

    public TransactionService(TransactionRepository transactionRepository, UserService userService) {
        this.transactionRepository = transactionRepository;
        this.userService = userService;
    }

    public void createInitialTransaction(User user, Long amount) {
        var transaction = new Transaction();
        transaction.setFromUser(userService.getSystemUser());
        transaction.setToUser(user);
        transaction.setAmount(amount);
        transaction.setTimestamp(new Timestamp(System.currentTimeMillis()));
        transaction.setDescription("Initial balance for user: " + user.getName());

        // Save the transaction to the repository
        transactionRepository.save(transaction);
    }

    public void createTransaction(CreateTransactionDto dto) {
        // Retrieve users from the user service
        User fromUser = userService.getUser(dto.getFromUserId());
        User toUser = userService.getUser(dto.getToUserId());
        var transaction = new Transaction();
        transaction.setFromUser(fromUser);
        transaction.setToUser(toUser);
        transaction.setAmount(dto.getAmount());
        transaction.setTimestamp(new Timestamp(System.currentTimeMillis()));
        transaction.setDescription(dto.getDescription());

        // Save the transaction to the repository
        transactionRepository.save(transaction);
    }

    public Transaction getTransaction(Long id) {
        // Retrieve a transaction by ID from the repository
        return transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found with id: " + id));
    }

    public Page<Transaction> getAllTransactions(Pageable pageable) {
        // Retrieve all transactions with pagination
        return transactionRepository.findAll(pageable);
    }

    public Page<Transaction> getUserTransactions(Long userId, Pageable pageable) {
        // Retrieve all transactions for a specific user with pagination
        User user = userService.getUser(userId);
        return transactionRepository.findAllByFromUserOrToUser(user, user, pageable);
    }

    public Page<Transaction> getTransactionsByDateRange(Timestamp startDate, Timestamp endDate, Pageable pageable) {
        // Retrieve transactions within a specific date range with pagination
        return transactionRepository.findAllByTimestampBetween(startDate, endDate, pageable);
    }

    public Long getUserBalance(User user) {
        // Calculate the user's balance by summing all transactions involving the user
        return getUserTransactions(user.getId(), Pageable.unpaged())
                .stream()
                .mapToLong(transaction -> transaction.getFromUser().equals(user) ? -transaction.getAmount() : transaction.getAmount())
                .sum();
    }
}
