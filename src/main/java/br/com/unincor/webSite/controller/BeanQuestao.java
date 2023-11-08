package br.com.unincor.webSite.controller;

import br.com.unincor.webSite.model.dao.AtividadeDao;
import br.com.unincor.webSite.model.dao.ProfessorDao;
import br.com.unincor.webSite.model.dao.QuestaoDao;
import br.com.unincor.webSite.model.dao.QuestaoFechadaDao;
import br.com.unincor.webSite.model.domain.Atividade;
import br.com.unincor.webSite.model.domain.Disciplina;
import br.com.unincor.webSite.model.domain.Questao;
import br.com.unincor.webSite.model.domain.QuestaoFechada;
import br.com.unincor.webSite.model.domain.TipoQuestão;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
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
public class BeanQuestao implements Serializable {

    //CRUD com questão / salvar questão fechada / salvar questão atividade
    private Questao questao;
    private Atividade atividadeSelecionada;
    private List<Questao> questoes = new ArrayList<>();
    private List<Atividade> atividades = new ArrayList<>();

    @PostConstruct
    public void init() {
        buscar();
    }

    public void salvar() {
        if (atividadeSelecionada != null) {
            questao.getAtividades().clear(); // Remove todas as disciplinas associadas
            questao.getAtividades().add(atividadeSelecionada); // Adiciona a nova disciplina selecionada
        }

//        new QuestaoDao().save(questao);
        cancelar();
        buscar();

    }

    public void buscar() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        var professorLogado = new ProfessorDao().findById((Long) session.getAttribute("professorId"));
        this.questoes = new QuestaoDao().buscarQuestaosProfessorPorLogin(professorLogado);
    }

    public void cancelar() {
        this.questao = null;
    }

    public void limparTabela() {
        this.questoes = null;
    }

    public void editar(Questao questao) {
        this.questao = questao;
        this.atividadeSelecionada = questao.getAtividades().get(0);
    }

    public void novo() {
        this.questao = new Questao();
    }

    public void remover(Questao questao) {
        System.out.println(questao.getEnunciado());
        new QuestaoDao().delete(questao.getId());

        buscar();
    }

    public List<Atividade> getAtividades() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        var professorLogado = new ProfessorDao().findById((Long) session.getAttribute("professorId"));
        return this.atividades = new AtividadeDao().buscarAtividadesProfessorPorLoginComQuestoes(professorLogado);
    }

}
