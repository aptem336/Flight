package model;

import converter.DateConverter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Flight {
    private static final Integer ECONOMY_PLACE_COUNT = 100;
    private static final Integer BUSINESS_PLACE_COUNT = 100;
    private static final Integer ECONOMY_PLACE_PRICE = 3500;
    private static final Integer BUSINESS_PLACE_PRICE = 5000;
    @NotNull
    @Column(nullable = false)
    private final Integer economyPlaceCount;
    @NotNull
    @Column(nullable = false)
    private final Integer businessPlaceCount;
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
        this(ECONOMY_PLACE_COUNT, BUSINESS_PLACE_COUNT,
                ECONOMY_PLACE_PRICE, BUSINESS_PLACE_PRICE);
    }

    public Flight(int economyPlaceCount, int businessPlaceCount,
                  int economyPlacePrice, int businessPlacePrice) {
        this.economyPlaceCount = economyPlaceCount;
        this.businessPlaceCount = businessPlaceCount;
        for (int i = 0; i < economyPlaceCount; i++) {
            Place place = new Place();
            place.setPrice(economyPlacePrice);
            place.setFlight(this);
            places.add(place);
        }
        for (int i = 0; i < businessPlaceCount; i++) {
            Place place = new Place();
            place.setPrice(businessPlacePrice);
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

    public Integer getEconomyPlaceCount() {
        return economyPlaceCount;
    }

    public Integer getBusinessPlaceCount() {
        return businessPlaceCount;
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

    @Transient
    public List<Place> getEconomyPlaces() {
        return getPlaces().subList(0, ECONOMY_PLACE_COUNT);
    }

    @Transient
    public List<Place> getBusinessPlaces() {
        return getPlaces().subList(ECONOMY_PLACE_COUNT, ECONOMY_PLACE_COUNT + BUSINESS_PLACE_COUNT);
    }

    @Transient
    public boolean isEconomyEnabled() {
        return getEconomyPlaces().stream().anyMatch(place -> place.getPerson() == null);
    }

    @Transient
    public boolean isBusinessEnabled() {
        return getBusinessPlaces().stream().anyMatch(place -> place.getPerson() == null);
    }
}
