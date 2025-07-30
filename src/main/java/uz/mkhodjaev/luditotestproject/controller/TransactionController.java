package uz.mkhodjaev.luditotestproject.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.mkhodjaev.luditotestproject.dto.CreateTransactionDto;
import uz.mkhodjaev.luditotestproject.model.Transaction;
import uz.mkhodjaev.luditotestproject.service.TransactionService;

import java.sql.Timestamp;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createTransaction(@RequestBody CreateTransactionDto dto) {
        transactionService.createTransaction(dto);
        return ResponseEntity.ok("Transaction created successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransaction(@PathVariable Long id) {
        var transaction = transactionService.getTransaction(id);
        return ResponseEntity.ok(transaction);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<Transaction>> getAllTransactions(Pageable pageable) {
        var transactions = transactionService.getAllTransactions(pageable);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/all/by-user/{userId}")
    public ResponseEntity<Page<Transaction>> getUserTransactions(@PathVariable Long userId, Pageable pageable) {
        var transactions = transactionService.getUserTransactions(userId, pageable);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/all/by-date-range")
    public ResponseEntity<Page<Transaction>> getTransactionsByDateRange(
            @RequestParam Timestamp startDate,
            @RequestParam Timestamp endDate,
            Pageable pageable) {
        var transactions = transactionService.getTransactionsByDateRange(startDate, endDate, pageable);
        return ResponseEntity.ok(transactions);
    }
}
