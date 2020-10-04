package controller;

import model.Person;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

@Named
@Stateless
public class PersonalArea {
    @Inject
    private EntityManager em;
    private Person person;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String save() {
        em.merge(person);
        return "flight_filter?faces-redirect=true";
    }
}
