package model;

import converter.DateConverter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Person {
    @Id
    private String passport;
    @NotNull
    @Column(nullable = false)
    private String fullName;
    @NotNull
    @Column(nullable = false)
    @Convert(converter = DateConverter.class)
    private Date birthDate;
    @NotNull
    @Column(nullable = false)
    @Enumerated
    private Gender gender;
    @ManyToOne(optional = false)
    private Country country;

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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public enum Gender {
        MALE("муж."), FEMALE("жен.");

        private final String label;

        Gender(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }
}
