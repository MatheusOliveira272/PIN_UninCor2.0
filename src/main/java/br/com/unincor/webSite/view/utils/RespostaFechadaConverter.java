package br.com.unincor.webSite.view.utils;

import br.com.unincor.webSite.model.dao.QuestaoFechadaDao;
import br.com.unincor.webSite.model.domain.QuestaoFechada;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("respostaFechadaConverter")
//@FacesConverter(forClass = QuestaoFechada.class)
public class RespostaFechadaConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && !value.isBlank()) {
            return Long.valueOf(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        if (value instanceof Long) {
            return String.valueOf((Long) value);
        }
        return "";
    }

//    @Override
//    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
//        if (value != null && !value.isBlank()) {
//           
//            var test = new QuestaoFechadaDao().findById(Long.valueOf(value));
//             System.out.println("1" + test);
//            return test;
//        }
//        return null;
//    }
//
//    @Override
//    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
//        if (value instanceof QuestaoFechada) {
//            QuestaoFechada questaoFechada = (QuestaoFechada) value;
//            System.out.println("2" + String.valueOf(questaoFechada.getId()));
//            return String.valueOf(questaoFechada.getId());
//        }
//        return "";
//    }
//    @Override
//    public Object getAsObject(FacesContext context, UIComponent component, String value) {
//        if (value != null && !value.isEmpty()) {
//            return new QuestaoFechadaDao().findById(Long.valueOf(value));
//        }
//        return null;
//    }
//
//    @Override
//    public String getAsString(FacesContext context, UIComponent component, Object value) {
//        if (value instanceof QuestaoFechada) {
//            QuestaoFechada questaoFechada = (QuestaoFechada) value;
//            return String.valueOf(questaoFechada.getId());
//        }
//        return "";
//    }
//    
//    @Override
//    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
//        System.out.println("Estou no getAsObject");
//        System.out.println(value);
//        if (value != null && !value.isBlank()) {
//            var qf = new QuestaoFechadaDao().findById(Long.valueOf(value));
//              System.out.println("1" + qf.getId());
//            return qf.getId();
//         
//        }
//        return "";
//    }
//
//    @Override
//    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
//        System.out.println("Estou no getAsString");
//        System.out.println(String.valueOf(value));
//        if (value instanceof QuestaoFechada) {
//            QuestaoFechada qf = (QuestaoFechada) value;
//            System.out.println("2 "+ qf.getId().toString());
//            return qf.getId().toString();
//        }
//        return "";
//    }
}
