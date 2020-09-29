package model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    @ManyToOne(optional = false)
    private Flight flight;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Person person;

    public String getId() {
        return id;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Place place = (Place) o;
        return Objects.equals(getId(), place.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
