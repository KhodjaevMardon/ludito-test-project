package uz.mkhodjaev.luditotestproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.mkhodjaev.luditotestproject.dto.UserDto;
import uz.mkhodjaev.luditotestproject.model.User;
import uz.mkhodjaev.luditotestproject.service.TransactionService;
import uz.mkhodjaev.luditotestproject.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final TransactionService transactionService;

    public UserController(UserService userService, TransactionService transactionService) {
        this.userService = userService;
        this.transactionService = transactionService;
    }

    @PostMapping("/create")
    @Operation(summary = "Создать пользователя",
               description = "Создает нового пользователя и устанавливает начальный баланс")
    public ResponseEntity<Long> createUser(@RequestBody UserDto dto, @RequestParam Long initialBalance) {
        var user = userService.createUser(dto);
        transactionService.createInitialTransaction(user, initialBalance);
        return ResponseEntity.ok(user.getId());
    }

    @GetMapping("/all")
    @Operation(summary = "Получить всех пользователей",
               description = "Возвращает список всех пользователей с пагинацией")
    public ResponseEntity<Page<User>> getAllUsers(Pageable pageable) {
        Page<User> users = userService.getUsers(pageable);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
@Operation(summary = "Получить пользователя по ID",
           description = "Возвращает информацию о пользователе по заданному ID")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User user = userService.getUser(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/update/{id}")
    @Operation(summary = "Обновить пользователя",
               description = "Обновляет информацию о пользователе по заданному ID")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody String name) {
        userService.updateUser(id, name);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/balance")
    @Operation(summary = "Получить баланс пользователя",
               description = "Возвращает текущий баланс пользователя по его ID")
    public ResponseEntity<Long> getUserBalance(@PathVariable Long id) {
        User user = userService.getUser(id);
        Long balance = transactionService.getUserBalance(user);
        return ResponseEntity.ok(balance);
    }
}
