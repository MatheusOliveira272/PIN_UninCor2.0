/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.unincor.webSite.controller;

import br.com.unincor.webSite.model.dao.AlunoAtividadeDao;
import br.com.unincor.webSite.model.dao.AlunoDao;
import br.com.unincor.webSite.model.dao.AtividadeDao;
import br.com.unincor.webSite.model.domain.AlunoAtividade;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import lombok.Getter;
import lombok.Setter;

@ManagedBean
@ViewScoped
@Getter
@Setter
public class BeanAlunoAtividade implements Serializable{
    
    String codigo;
    public void adicionaAlunoAtividade(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        var alunoLogado = new AlunoDao().buscarAlunoPorLogin((String) session.getAttribute("email"));
        var atividadeCodigo = new AtividadeDao().buscarAtividadeCodigo(codigo);
        var alunoAtividade = new AlunoAtividade(null, null, alunoLogado, atividadeCodigo);
        AlunoAtividadeDao alunoAtividadeDao = new AlunoAtividadeDao();
        alunoAtividadeDao.save(alunoAtividade);
    }
}
