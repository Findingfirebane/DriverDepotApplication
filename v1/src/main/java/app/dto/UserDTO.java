package app.dto;

import java.io.Serializable;

public class UserDTO implements Serializable{



    private int id;
    private String username;
    private String email;
    private String password;
    private int role_id;


    
    
    public UserDTO(String username2, String email2, String password2, Integer role_id2) {
        //TODO Auto-generated constructor stub
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }  
    
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getRole_id() {
        return role_id;
    }
    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }
}
