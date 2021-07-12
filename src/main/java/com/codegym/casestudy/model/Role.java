package com.codegym.casestudy.model;
import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long role_id;
    private String role_name;
    @OneToMany(targetEntity = User.class, fetch = FetchType.EAGER)
    private List<User> users;
    public Role() {
    }
    public Role(Long role_id, String role_name) {
        this.role_id = role_id;
        this.role_name = role_name;
    }
    public Role(Long role_id, String role_name, List<User> users) {
        this.role_id = role_id;
        this.role_name = role_name;
        this.users = users;
    }
    public Long getRole_id() {
        return role_id;
    }
    public void setRole_id(Long role_id) {
        this.role_id = role_id;
    }
    public String getRole_name() {
        return role_name;
    }
    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }
    public List<User> getUsers() {
        return users;
    }
    public void setUsers(List<User> users) {
        this.users = users;
    }
}