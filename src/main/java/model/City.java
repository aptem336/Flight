package model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    @NotNull
    @Column(nullable = false)
    private String name;
    @ManyToOne(optional = false)
    private Country country;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
