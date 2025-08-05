package uz.mkhodjaev.luditotestproject.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "transactions")
@SequenceGenerator(name = "transaction_sequence", sequenceName = "transaction_sequence", allocationSize = 1)
public class Transaction {
    @Id
    @GeneratedValue(generator = "transaction_sequence")
    private Long id; // Unique identifier for the transaction

    @ManyToOne
    @JoinColumn(name = "from_user", nullable = false)
    private User fromUser; // ID of the user who initiated the transaction

    @ManyToOne
    @JoinColumn(name = "to_user", nullable = false)
    private User toUser; // ID of the user who received the transaction

    @Column(nullable = false)
    private Long amount; // Amount of money transferred in the transaction

    @Column(nullable = false)
    private Date timestamp; // Timestamp of when the transaction occurred

    @Column(nullable = false)
    private String description; // Description of the transaction, e.g., "Payment for services"

    public Transaction(Long id, User fromUser, User toUser, Long amount, Date timestamp, String description) {
        this.id = id;
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.amount = amount;
        this.timestamp = timestamp;
        this.description = description;
    }

    public Transaction() {
        // Default constructor for JPA
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
