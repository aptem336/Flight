package controller;

import model.Flight;

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
    private ListController listController;
    private List<Flight> flights;

    @PostConstruct
    private void postConstruct() {
        flights = listController.getFlights();
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
        List<Flight> persistedFlights = listController.getFlights();
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
