/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.unincor.webSite.controller;

import br.com.unincor.webSite.model.dao.AtividadeDao;
import br.com.unincor.webSite.model.dao.ProfessorDao;
import br.com.unincor.webSite.model.dao.QuestaoFechadaDao;
import br.com.unincor.webSite.model.domain.Questao;
import br.com.unincor.webSite.model.domain.QuestaoFechada;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import lombok.Getter;
import lombok.Setter;

@ManagedBean
@ViewScoped
@Getter
@Setter
public class BeanQuestaoFechada {
    @ManagedProperty(value = "#{beanQuestao}")
    private BeanQuestao beanQuestao;
            
    private List<QuestaoFechada> questoesFechadas = new ArrayList<>();
    private QuestaoFechada questaoFechada;
    private List<QuestaoFechada> recuperaQuestoesFechadas = new ArrayList<>();
    
    @PostConstruct
    public void init(){
        questaoFechada = new QuestaoFechada();
        questoesFechadas = new ArrayList<>();
        buscar();
    }
    
    public void salvar(Questao questao){
        
        new QuestaoFechadaDao().salvaAlernativasQuestoaFechada(questoesFechadas);
        if(questao != null){
            questao.getQuestaoFechadas().clear();
            if(!questoesFechadas.isEmpty()) {
                    questoesFechadas.forEach(q -> q.setQuestao(questao));
            }
        }
    }
    
    public void salvaTudo(Questao questao){
        beanQuestao.salvar();
        salvar(questao);
    }
    
    
    
    //Aqui para baixo estou adicionando as alternativas da questão fechada à questão 
    public void adicionarQuestaoFechada() {
       questoesFechadas.add(questaoFechada);
         questaoFechada = new QuestaoFechada(); // Cria uma nova instância para a próxima questão fechada
        
        
       
    }
    
    public void cancelar(){
        this.questoesFechadas = null;
    }
    
    public void editar(QuestaoFechada questaoFechada){
        this.questaoFechada = questaoFechada;
    }
    
    public void remover(QuestaoFechada questaoFechada) {
        System.out.println(questaoFechada.getDescricao());
        new QuestaoFechadaDao().delete(questaoFechada.getId());

        buscar();

    }
    
    public void buscar() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        var professorLogado = new ProfessorDao().findById((Long) session.getAttribute("professorId"));
        this.recuperaQuestoesFechadas = new QuestaoFechadaDao().buscaQuestaoFechadaPorProfessor(professorLogado);
    }
}
