package controller;

import model.Flight;
import model.Person;
import model.Place;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.Serializable;

@Named
@ViewScoped
public class ReservationsController implements Serializable {
    @Inject
    private EntityManager em;
    private Flight flight;
    private Place place;
    private Person person;

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
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

    @Transactional
    public String save() {
        place.setPerson(person);
        em.merge(place);
        return "flights?faces-redirect=true";
    }
}
