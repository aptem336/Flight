package converter;

import model.Place;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

@Named
public class PlaceConverter implements Converter<Place> {
    @Inject
    private EntityManager em;

    @Override
    public Place getAsObject(FacesContext facesContext, UIComponent uiComponent, String id) {
        return id == null ? null : em.find(Place.class, id);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Place place) {
        return place == null ? null : place.getId();
    }
}
