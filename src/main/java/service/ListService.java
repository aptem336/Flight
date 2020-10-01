package service;

import model.Airport;
import model.Country;
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

    public List<Country> getCountry() {
        return getAll(Country.class);
    }

    public List<Airport> getAirports() {
        return getAll(Airport.class);
    }

    public List<Flight> getFlights() {
        return getAll(Flight.class);
    }

    private <T> List<T> getAll(Class<T> tClass) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(tClass);
        Root<T> rootEntry = cq.from(tClass);
        CriteriaQuery<T> all = cq.select(rootEntry);
        TypedQuery<T> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }
}
