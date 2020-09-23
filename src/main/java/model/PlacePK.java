package model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Embeddable
public class PlacePK implements Serializable {
    @NotNull
    @Column(nullable = false)
    private Integer row;
    @NotNull
    @Column(nullable = false)
    private Integer number;
    @NotNull
    @Column(nullable = false)
    private String flightId;
}
