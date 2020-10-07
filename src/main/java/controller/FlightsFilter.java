package controller;

import model.Flight;
import service.ListService;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.security.enterprise.SecurityContext;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Named
@ViewScoped
public class FlightsFilter implements Serializable {
    @Inject
    private EntityManager em;
    @Inject
    private SecurityContext securityContext;
    @Inject
    private ListService listService;
    private List<Flight> flights;
    private Flight referenceFlight;

    @PostConstruct
    private void postConstruct() {
        referenceFlight = new Flight();
        flights = listService.getFlights();
    }

    public List<Flight> getFlights() {
        Stream<Flight> flightStream = flights.stream();
        if (referenceFlight.getFromAirport() != null) {
            flightStream = flightStream.filter(f -> f.getFromAirport().equals(referenceFlight.getFromAirport()));
        }
        if (referenceFlight.getToAirport() != null) {
            flightStream = flightStream.filter(f -> f.getToAirport().equals(referenceFlight.getToAirport()));
        }
        if (referenceFlight.getDate() != null) {
            flightStream = flightStream.filter(f -> f.getDate().equals(referenceFlight.getDate()));
        }
        if (referenceFlight.getPlane() != null && !referenceFlight.getPlane().isEmpty()) {
            flightStream = flightStream.filter(f -> f.getPlane().contains(referenceFlight.getPlane()));
        }
        return flightStream.collect(Collectors.toList());
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public Flight getReferenceFlight() {
        return referenceFlight;
    }

    public void setReferenceFlight(Flight referenceFlight) {
        this.referenceFlight = referenceFlight;
    }

    public void add() {
        flights.add(new Flight());
    }

    public void remove(Flight flight) {
        flights.remove(flight);
    }

    @Transactional
    public void save() {
        List<Flight> persistedFlights = listService.getFlights();
        for (Flight persistedFlight : persistedFlights) {
            if (!flights.contains(persistedFlight)) {
                em.remove(persistedFlight);
            }
        }
        for (Flight flight : flights) {
            em.merge(flight);
        }
        flights = listService.getFlights();
    }

    public SecurityContext getSecurityContext() {
        return securityContext;
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "flight_filter?faces-redirect=true";
    }
}
