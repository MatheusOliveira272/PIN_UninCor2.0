package br.com.unincor.webSite.view.utils;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("questaoConverter")
public class QuestaoConverter implements Converter {

//    @Override
//public Object getAsObject(FacesContext context, UIComponent component, String value) {
//    return value != null && "A".equals(value); // Retorna true se o valor for "A", caso contrário retorna false
//}
 @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return "A".equals(value); // Retorna true se o valor for "A"
    }

    @Override
public String getAsString(FacesContext context, UIComponent component, Object value) {
    if (value != null && (Boolean) value) {
        return "A";
    } else {
        return "B";
    }
}

}


