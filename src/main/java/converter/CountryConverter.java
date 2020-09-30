package converter;

import model.Country;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

@Named
public class CountryConverter implements Converter<Country> {
    @Inject
    private EntityManager em;

    @Override
    public Country getAsObject(FacesContext facesContext, UIComponent uiComponent, String id) {
        return id == null ? null : em.find(Country.class, id);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Country country) {
        return country == null ? null : country.getId();
    }
}
