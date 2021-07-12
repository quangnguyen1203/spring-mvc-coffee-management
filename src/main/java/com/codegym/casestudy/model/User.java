package com.codegym.casestudy.model;
import javax.persistence.*;
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;
    private String user_name;
    private String password;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String phoneNumber;
    private String image;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    public User() {
    }
    public User(Long user_id, String user_name, String password, Role role) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.password = password;
        this.role = role;
    }
    public User(Long user_id, String user_name, String password, String email, String phoneNumber, String image, Role role) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.image = image;
        this.role = role;
    }
    public User(String user_name, String password, String email, String phoneNumber, String image, Role role) {
        this.user_name = user_name;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.image = image;
        this.role = role;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public Long getUser_id() {
        return user_id;
    }
    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
    public String getUser_name() {
        return user_name;
    }
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
}