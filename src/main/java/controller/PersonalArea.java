package controller;

import model.Person;
import model.Users;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.security.enterprise.SecurityContext;

@Named
@RequestScoped
public class PersonalArea {
    @Inject
    private EntityManager em;
    @Inject
    private SecurityContext securityContext;
    private Users users;

    @PostConstruct
    private void postConstruct() {
        users = em.find(Users.class, securityContext.getCallerPrincipal().getName());
    }

    public Person getPerson() {
        if (users.getPerson() == null) {
            users.setPerson(new Person());
        }
        return users.getPerson();
    }

    public String save() {
        em.merge(users);
        return "flight_filter?faces-redirect=true";
    }

    public Person.Gender[] getGenderValues() {
        return Person.Gender.values();
    }
}
