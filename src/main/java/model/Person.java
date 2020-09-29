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

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
