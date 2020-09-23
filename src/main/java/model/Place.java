package model;

import javax.persistence.*;

@Entity
public class Place {
    @EmbeddedId
    private PlacePK placePK;
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @MapsId("flightId")
    private Flight flight;
}
