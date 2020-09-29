package controller;

import model.Flight;
import service.ListService;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class FlightsController implements Serializable {
    @Inject
    private EntityManager em;
    @Inject
    private ListService listService;
    private List<Flight> flights;

    @PostConstruct
    private void postConstruct() {
        flights = listService.getFlights();
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void add() {
        flights.add(new Flight());
    }

    public void remove(Flight flight) {
        flights.remove(flight);
    }

    @Transactional
    public String save() {
        List<Flight> persistedFlights = listService.getFlights();
        for (Flight persistedFlight : persistedFlights) {
            if (!flights.contains(persistedFlight)) {
                em.remove(persistedFlight);
            }
        }
        for (Flight flight : flights) {
            em.merge(flight);
        }
        return "flights?faces-redirect=true";
    }
}
