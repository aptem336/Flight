package service;

import model.Airport;
import model.Flight;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class ListService implements Serializable {
    @Inject
    private EntityManager em;

    public List<Flight> getFlights() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Flight> cq = cb.createQuery(Flight.class);
        Root<Flight> rootEntry = cq.from(Flight.class);
        CriteriaQuery<Flight> all = cq.select(rootEntry);
        TypedQuery<Flight> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }

    public List<Airport> getAirports() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Airport> cq = cb.createQuery(Airport.class);
        Root<Airport> rootEntry = cq.from(Airport.class);
        CriteriaQuery<Airport> all = cq.select(rootEntry);
        TypedQuery<Airport> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }
}
