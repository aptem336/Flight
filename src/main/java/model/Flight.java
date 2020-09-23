package model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private Airport fromAirport;
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
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

    public Airport getToAirport() {
        return toAirport;
    }

    public String getPlane() {
        return plane;
    }

    public Date getDate() {
        return date;
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
