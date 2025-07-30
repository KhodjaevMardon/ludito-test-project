package uz.mkhodjaev.luditotestproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.mkhodjaev.luditotestproject.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {}
