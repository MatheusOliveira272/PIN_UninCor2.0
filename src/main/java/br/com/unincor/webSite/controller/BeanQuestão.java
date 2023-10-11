package br.com.unincor.webSite.controller;

import br.com.unincor.webSite.model.dao.QuestaoDao;
import br.com.unincor.webSite.model.dao.ProfessorDao;
import br.com.unincor.webSite.model.domain.Questao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
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
public class BeanQuestão implements Serializable{
    
    private List<Questao> questaos = new ArrayList<>();
    private Questao questao;
  


    @PostConstruct
    public void init() {
        buscar();
    }

    public void salvar() {
        
               new QuestaoDao().save(questao);
          cancelar();
        buscar();
    }

    public void limparTabela() {
        questaos.clear();
    }

    public void editar(Questao questao) {
        this.questao = questao;
         
    }

    public void novo() {
        this.questao = new Questao();

    }

    public void cancelar() {
        this.questao = null;
    }

    public void remover(Questao questao) {
      
        new QuestaoDao().delete(questao.getId());
        
        buscar();
      

    }
       
 
  
   public void buscar() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        var professorLogado = new ProfessorDao().findById((Long) session.getAttribute("professorId"));
       // this.questaos = new QuestaoDao().buscarQuestaosProfessorPorLogin(professorLogado);
        

    }
 
   
   

 
    }
  
