package br.com.unincor.webSite.controller;

import br.com.unincor.webSite.model.dao.DisciplinaDao;
import br.com.unincor.webSite.model.domain.Disciplina;
import br.com.unincor.webSite.view.utils.Mensagens;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;

@ManagedBean
@SessionScoped
@Getter
@Setter
public class BeanDisciplina implements Serializable {

    private List<Disciplina> disciplinas = new ArrayList<>();
    private Disciplina disciplina;
    private boolean editando = false;

    public BeanDisciplina() {
        disciplina = new Disciplina();

    }

    public void salvar() {
        new DisciplinaDao().save(disciplina);
        cancelar();
        buscar();
        editando = false;
    }

  
    public void limparTabela() {
        disciplinas.clear();
    }

    public void editar(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public void novo() {
        this.disciplina = new Disciplina();

    }

    public void cancelar() {
        this.disciplina = null;
    }

    public void remover(Disciplina disciplina) {

        new DisciplinaDao().delete(disciplina.getId());
        buscar();

    }

    public void buscar() {
        this.disciplinas = new DisciplinaDao().findAll();

    }
}
