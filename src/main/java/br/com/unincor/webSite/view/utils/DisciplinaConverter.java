package br.com.unincor.webSite.view.utils;


import br.com.unincor.webSite.model.dao.DisciplinaDao;
import br.com.unincor.webSite.model.domain.Disciplina;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;



@FacesConverter("disciplinaConverter")
public class DisciplinaConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
         if(value != null && !value.isBlank()){
            var editora = new DisciplinaDao().findById(Long.valueOf(value));
            return editora;
            
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object velue) {
        if (velue instanceof Disciplina) {
            Disciplina disciplina = (Disciplina) velue;
            return disciplina.getId().toString();
        }
        return null;
    }
   
}
