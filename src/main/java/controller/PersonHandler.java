package controller;

import model.Person;
import model.Place;
import model.Users;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.security.enterprise.SecurityContext;
import javax.transaction.Transactional;
import java.io.Serializable;

@Named
@ViewScoped
public class PersonHandler implements Serializable {
    @Inject
    private EntityManager em;
    @Inject
    private SecurityContext securityContext;
    private Place place;
    private Person person;

    @PostConstruct
    private void postConstruct() {
        if (securityContext.getCallerPrincipal() != null) {
            if (em.find(Users.class, securityContext.getCallerPrincipal()).getPerson() != null) {
                person = em.find(Users.class, securityContext.getCallerPrincipal()).getPerson();
            }
        }
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public Person getPerson() {
        if (person == null) {
            person = new Person();
        }
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Person.Gender[] getGenderValues() {
        return Person.Gender.values();
    }

    @Transactional
    public String reserve() {
        place.setPerson(person);
        place.setPrice(place.getFlight().getPlacePrice(place.getPlaceClass()));/*цена билета определяется в момент покупки*/
        em.merge(place);
        return "flight_filter?faces-redirect=true";
    }
}
