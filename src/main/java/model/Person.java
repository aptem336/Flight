package model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Person {
    @Id
    private String passport;
    @NotNull
    @Column(nullable = false)
    private String fullName;
    @ManyToOne
    private Profile profile;
}
