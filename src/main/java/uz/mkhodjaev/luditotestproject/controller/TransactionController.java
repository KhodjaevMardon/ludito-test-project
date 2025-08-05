package uz.mkhodjaev.luditotestproject.controller;

import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Создать транзакцию",
               description = "Создает новую транзакцию между пользователями")
    public ResponseEntity<?> createTransaction(@RequestBody CreateTransactionDto dto) {
        transactionService.createTransaction(dto);
        return ResponseEntity.ok("Transaction created successfully");
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить транзакцию по ID",
               description = "Возвращает информацию о транзакции по заданному ID")
    public ResponseEntity<Transaction> getTransaction(@PathVariable Long id) {
        var transaction = transactionService.getTransaction(id);
        return ResponseEntity.ok(transaction);
    }

    @GetMapping("/all")
    @Operation(summary = "Получить все транзакции",
               description = "Возвращает список всех транзакций с пагинацией")
    public ResponseEntity<Page<Transaction>> getAllTransactions(Pageable pageable) {
        var transactions = transactionService.getAllTransactions(pageable);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/all/by-user/{userId}")
    @Operation(summary = "Получить транзакции пользователя",
               description = "Возвращает список транзакций для указанного пользователя с пагинацией")
    public ResponseEntity<Page<Transaction>> getUserTransactions(@PathVariable Long userId, Pageable pageable) {
        var transactions = transactionService.getUserTransactions(userId, pageable);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/all/by-date-range")
    @Operation(summary = "Получить транзакции по диапазону дат",
               description = "Возвращает список транзакций в заданном диапазоне дат с пагинацией")
    public ResponseEntity<Page<Transaction>> getTransactionsByDateRange(
            @RequestParam Timestamp startDate,
            @RequestParam Timestamp endDate,
            Pageable pageable) {
        var transactions = transactionService.getTransactionsByDateRange(startDate, endDate, pageable);
        return ResponseEntity.ok(transactions);
    }
}
