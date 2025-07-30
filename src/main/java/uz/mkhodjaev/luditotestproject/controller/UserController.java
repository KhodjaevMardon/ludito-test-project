package uz.mkhodjaev.luditotestproject.controller;

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
    public ResponseEntity<Long> createUser(@RequestBody UserDto dto, @RequestParam Long initialBalance) {
        var user = userService.createUser(dto);
        transactionService.createInitialTransaction(user, initialBalance);
        return ResponseEntity.ok(user.getId());
    }

    @GetMapping("/all")
    public ResponseEntity<Page<User>> getAllUsers(Pageable pageable) {
        Page<User> users = userService.getUsers(pageable);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User user = userService.getUser(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody String name) {
        userService.updateUser(id, name);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/balance")
    public ResponseEntity<Long> getUserBalance(@PathVariable Long id) {
        User user = userService.getUser(id);
        Long balance = transactionService.getUserBalance(user);
        return ResponseEntity.ok(balance);
    }
}
