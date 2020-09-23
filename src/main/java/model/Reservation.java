package model;

import javax.persistence.*;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    @ManyToOne(optional = false)
    private Person person;
    @ManyToOne(optional = false)
    private Place place;
    @ManyToOne(optional = false)
    private Flight flight;
}
