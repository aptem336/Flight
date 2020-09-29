package converter;

import model.Flight;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

@Named
public class FlightConverter implements Converter<Flight> {
    @Inject
    private EntityManager em;

    @Override
    public Flight getAsObject(FacesContext facesContext, UIComponent uiComponent, String id) {
        return id == null ? null : em.find(Flight.class, id);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Flight flight) {
        return flight == null ? null : flight.getId();
    }
}
