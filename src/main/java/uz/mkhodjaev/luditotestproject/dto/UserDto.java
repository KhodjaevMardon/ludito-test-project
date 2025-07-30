package uz.mkhodjaev.luditotestproject.dto;

public class UserDto {
    private String name;

    public UserDto() {
        // Default constructor for serialization/deserialization
    }

    public UserDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
