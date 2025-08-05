package uz.mkhodjaev.luditotestproject.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
@SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private Long id;

    @Column(nullable = false)
    private String name;

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public User() {
        // Default constructor for JPA
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
