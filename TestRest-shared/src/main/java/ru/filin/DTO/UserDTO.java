package ru.filin.DTO;

public class UserDTO {
    private long id;
    private String nickname;
    private String password;
    private String name;

    public UserDTO() {
    }

    public UserDTO(String nickname, String password, String name) {
        this.nickname = nickname;
        this.password = password;
        this.name = name;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
