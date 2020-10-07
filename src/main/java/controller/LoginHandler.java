package controller;

import model.Users;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

@Named
@RequestScoped
public class LoginHandler {
    @Inject
    private EntityManager em;
    @Inject
    private Pbkdf2PasswordHash passwordHash;
    private Users users;

    public Users getUsers() {
        if (users == null) {
            users = new Users();
        }
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public String register() {
        users.setPassword(passwordHash.generate(users.getPassword().toCharArray()));
        em.persist(users);
        return "personal_area?faces-redirect=true";
    }
}
