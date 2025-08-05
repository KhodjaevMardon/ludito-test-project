package uz.mkhodjaev.luditotestproject.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.mkhodjaev.luditotestproject.dto.UserDto;
import uz.mkhodjaev.luditotestproject.model.User;
import uz.mkhodjaev.luditotestproject.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final static String SYSTEM_USER_NAME = "System User";
    private final static Long SYSTEM_USER_ID = 1L;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(UserDto dto) {
        // Create a new user and save it to the repository
        User user = new User();
        user.setName(dto.getName());

        userRepository.save(user);

        return user;
    }

    public void createSystemUser() {
        // Check if the system user already exists
        if (userRepository.existsById(SYSTEM_USER_ID)) {
            return; // System user already exists, no need to create again
        }
        User user = new User();
        user.setId(SYSTEM_USER_ID);
        user.setName(SYSTEM_USER_NAME);

        userRepository.save(user);
    }

    public User getSystemUser() {
        // Retrieve the system user by ID from the repository
        var systemUserNotFound = userRepository.findById(1L);
        if (systemUserNotFound.isEmpty()) {
            createSystemUser(); // Create the system user if it does not exist
            systemUserNotFound = userRepository.findById(1L);
        }
        return systemUserNotFound.orElseThrow(() -> new RuntimeException("System user not found"));
    }

    public User getUser(Long id) {
        // Retrieve a user by ID from the repository
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public void updateUser(Long id, String name) {
        // Update the user's name and save it to the repository
        User user = getUser(id);
        user.setName(name);
        userRepository.save(user);
    }

    public Page<User> getUsers(Pageable pageable) {
        // Retrieve a paginated list of users from the repository
        return userRepository.findAll(pageable);
    }
}
