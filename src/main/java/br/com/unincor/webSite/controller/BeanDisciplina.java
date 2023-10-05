package br.com.unincor.webSite.controller;

import br.com.unincor.webSite.model.dao.DisciplinaDao;
import br.com.unincor.webSite.model.domain.Disciplina;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import lombok.Getter;
import lombok.Setter;

@ManagedBean
@ViewScoped
@Getter
@Setter
public class BeanDisciplina implements Serializable {

    private List<Disciplina> disciplinas = new ArrayList<>();
    private Disciplina disciplina;
    
    @PostConstruct
    public void init() {
        buscar();
    }

    public void salvar() {
        new DisciplinaDao().save(disciplina);
        cancelar();
        buscar();
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
