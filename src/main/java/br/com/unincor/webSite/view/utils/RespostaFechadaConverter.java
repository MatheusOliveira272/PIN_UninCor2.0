
package br.com.unincor.webSite.view.utils;

import br.com.unincor.webSite.model.dao.QuestaoFechadaDao;
import br.com.unincor.webSite.model.domain.QuestaoFechada;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;



@FacesConverter("respostaFechadaConverter")
public class RespostaFechadaConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
         if (value != null && !value.isBlank()) {
            var questaoFechada = new QuestaoFechadaDao().findById(Long.valueOf(value));
            return questaoFechada;

        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
         if (value instanceof QuestaoFechada) {
            QuestaoFechada alternativa = (QuestaoFechada) value;
            return alternativa.getId().toString();
        }
        return null;
    }
       
}
