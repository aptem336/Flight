package model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

@Entity
public class Flight {
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
}
