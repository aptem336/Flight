package model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
public class Flight {
    private static final int DEFAULT_PLACE_COUNT = 200;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    @ManyToOne(optional = false)
    private Airport fromAirport;
    @ManyToOne(optional = false)
    private Airport toAirport;
    @NotNull
    @Column(nullable = false)
    private String plane;
    @NotNull
    @Column(nullable = false)
    @Convert(converter = DateConverter.class)
    private Date date;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "flight")
    private List<Place> places = new ArrayList<>();

    public Flight() {
        for (int row = 0; row < DEFAULT_PLACE_COUNT; row++) {
            Place place = new Place();
            place.setFlight(this);
            places.add(place);
        }
    }

    public String getId() {
        return id;
    }

    public Airport getFromAirport() {
        return fromAirport;
    }

    public void setFromAirport(Airport fromAirport) {
        this.fromAirport = fromAirport;
    }

    public Airport getToAirport() {
        return toAirport;
    }

    public void setToAirport(Airport toAirport) {
        this.toAirport = toAirport;
    }

    public String getPlane() {
        return plane;
    }

    public void setPlane(String plane) {
        this.plane = plane;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Place> getPlaces() {
        return places;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return Objects.equals(getId(), flight.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Converter(autoApply = true)
    public static class DateConverter implements AttributeConverter<Date, Long> {
        @Override
        public Long convertToDatabaseColumn(Date date) {
            return date.getTime();
        }

        @Override
        public Date convertToEntityAttribute(Long timestamp) {
            return new Date(timestamp);
        }
    }
}
