package model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    @NotNull
    @Column(nullable = false)
    private String name;
}
