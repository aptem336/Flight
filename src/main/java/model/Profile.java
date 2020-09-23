package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Profile {
    @Id
    private String login;
    @NotNull
    @Column(nullable = false)
    private String password;
    @OneToMany(orphanRemoval = false, mappedBy = "profile")
    private List<Person> persons = new ArrayList<>();
}
