package uz.mkhodjaev.luditotestproject.dto;

public class CreateTransactionDto {
    private Long fromUserId;
    private Long toUserId;
    private Long amount;
    private String description;

    public CreateTransactionDto(Long fromUserId, Long toUserId, Long amount, String description) {
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.amount = amount;
        this.description = description;
    }

    public CreateTransactionDto() {
        // Default constructor
    }

    public Long getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Long fromUserId) {
        this.fromUserId = fromUserId;
    }

    public Long getToUserId() {
        return toUserId;
    }

    public void setToUserId(Long toUserId) {
        this.toUserId = toUserId;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
