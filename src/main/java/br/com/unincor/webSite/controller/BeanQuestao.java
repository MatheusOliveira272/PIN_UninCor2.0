package br.com.unincor.webSite.controller;

import br.com.unincor.webSite.model.dao.ProfessorDao;
import br.com.unincor.webSite.model.dao.QuestaoDao;
import br.com.unincor.webSite.model.domain.Questao;
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
import org.primefaces.PrimeFaces;

@ManagedBean
@ViewScoped
@Getter
@Setter
public class BeanQuestao implements Serializable {

    private List<Questao> questoes = new ArrayList<>();
    private Questao questao;
    private String tipoQuestao;
    private List<String> tiposQuestao;

    @PostConstruct
    public void init() {
        buscar();
    }

    public void salvar() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        //Long professorId = (Long) session.getAttribute("professorId");
        // var professorLogado = new ProfessorDao().findById((Long) session.getAttribute("professorId"));

        // disciplina.setProfessor(professorLogado);
        new QuestaoDao().save(questao);
        cancelar();
        //buscar();
    }

    public void limparTabela() {
        questoes.clear();
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
        /* FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        var professorLogado = new ProfessorDao().findById((Long) session.getAttribute("professorId"));
        this.questoes = new QuestaoDao().buscarQuestaosProfessorPorLogin(professorLogado);
         */

    }
    
     public List<TipoQuestão> getTipoQuestao() {
        return Arrays.asList(TipoQuestão.values());
    }

    /*

    public void abrirOuFecharDialogo() {
        if ("opcao1".equals(tipoQuestao)) {
            PrimeFaces.current().executeScript("PF('dlg1').show()");
            PrimeFaces.current().executeScript("PF('dlg2').hide()");
        } else if ("opcao2".equals(tipoQuestao)) {
            PrimeFaces.current().executeScript("PF('dlg1').hide()");
            PrimeFaces.current().executeScript("PF('dlg2').show()");
        } else {
            PrimeFaces.current().executeScript("PF('dlg1').hide()");
            PrimeFaces.current().executeScript("PF('dlg2').hide()");
        }
    }*/
}
