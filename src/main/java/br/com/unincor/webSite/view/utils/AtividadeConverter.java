/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.unincor.webSite.view.utils;

import br.com.unincor.webSite.model.dao.AtividadeDao;
import br.com.unincor.webSite.model.domain.Atividade;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("atividadeConverter")
public class AtividadeConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && !value.isBlank()) {
            var editora = new AtividadeDao().findById(Long.valueOf(value));
            return editora;

        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object velue) {
        if (velue instanceof Atividade) {
            Atividade disciplina = (Atividade) velue;
            return disciplina.getId().toString();
        }
        return null;
    }
}
