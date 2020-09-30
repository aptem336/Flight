package model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    @NotNull
    @Column(nullable = false)
    @Enumerated
    private PlaceClass placeClass;
    @NotNull
    @Column(nullable = false)
    private Integer price;

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

    public PlaceClass getPlaceClass() {
        return placeClass;
    }

    public void setPlaceClass(PlaceClass placeClass) {
        this.placeClass = placeClass;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
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

    public enum PlaceClass {
        ECONOMY("эконом"), BUSINESS("бизнес");

        private final String label;

        PlaceClass(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }
}
