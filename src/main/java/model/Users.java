package model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Users {
    @Id
    private String login;
    @NotNull
    @Column(nullable = false)
    private String password;
    @ManyToOne(optional = false)
    private Person person;
    @NotNull
    @Column(nullable = false)
    @Enumerated
    private Role role = Role.USER;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public enum Role {
        USER, SYSTEM
    }
}
