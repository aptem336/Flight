package controller;

import model.Airport;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

@Named
public class AirportController implements Converter<Airport> {
    @Inject
    private EntityManager em;
    @Override
    public Airport getAsObject(FacesContext facesContext, UIComponent uiComponent, String id) {
        return id == null ? null : em.find(Airport.class, id);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Airport airport) {
        return airport == null ? null : airport.getId();
    }
}
